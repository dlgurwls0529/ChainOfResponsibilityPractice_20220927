## 책임 연쇄 패턴
### 의미
어떤 요청을 보내고 처리하는 시스템이 있다고 할 때, 처리 절차을 조합하는 것(인증 필터, 특수문자 필터 등)과 처리 과정을 분리할 수 있도록 하는 패턴이다. 

분리를 해야 하는 이유는 Client에게 처리 과정이 노출될 경우 결합도가 낮아지기 때문이다. 무슨 이야기냐면, 반복적인 new와 생성자 주입을 통해서 객체를 생성하는 경우 : 

    public class ClientA {
        public void work() {
            Machine machine = new Machine(new Iron(new Temp(10)));
        }
    }
    
    public class ClientA {
        public void work() {
            Machine machine = new Machine(new Wood(new Temp(40)));
        }
    }
    
    public class ClientA {
        public void work() {
            Machine machine = new Machine(new Plastic(new Temp(20)));
        }
    }
    
Temp 클래스에서 생성자 필드가 바뀌는 경우 각 Client들에서 변경이 발생한다.(강한 결합) 반면 다음과 같이 고치면

    public class Container {
        public static getTemp() {
            return new Temp
        }
    }
