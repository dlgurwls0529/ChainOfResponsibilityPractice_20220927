## 책임 연쇄 패턴
### 의미
어떤 요청을 보내고 처리하는 시스템이 있다고 할 때, 처리 절차을 조합하는 것(인증 필터, 특수문자 필터 등)과 처리 과정을 분리할 수 있도록 하는 패턴이다. 

분리를 해야 하는 이유는 Client에게 처리 과정이 노출될 경우 결합도가 낮아지기 때문이다. 무슨 이야기냐면, 반복적인 new와 생성자 주입을 통해서 객체를 생성하는 경우 : 

    public class ClientA {
        public void work() {
            Machine machine = new Machine();
        }
    }
    
Temp 클래스에서 생성자 필드가 바뀌는 경우 각 Client들에서 변경이 발생한다.(강한 결합, 내용 결합) 반면, 다음과 같이 고치면

    public class Container {
        public static Machine getMachine() {
            return new Machine();
        }
    }
    
    public class ClientA {
        public void work() {
            Machine machine = Container.getMachine();
        }
    }

ClientA가 Machine 대신에 Container과 약한 결합 관계로 바뀌었다.(데이터 결합, 메소드 호출) 조삼모사인것 같지만 그게 아닌 이유는 후술하겠다. 가령, Machine 클래스가 없다면 ClientA가 안터지고 Container가 터지게 된다. 아니면 Machine 클래스에서 생성자 필드가 추가된다고 해도 Container에서

    public class Container {
        public static Machine getMachine() {
            return new Machine(10);
        }
    }
    
이런식으로 바꿔주면 된다. 변화를 단순화하면 다음과 같다.

    ClientA  --약한 의존-->  Container  --강한 의존-->  Machine
    ClientA  --강한 의존-->  Machine
    
어차피 별 차이 없는 것 같지만, Container 같은 클래스는(Spring의 설정 파일 같은) 굉장히 응집도가 높아서 관리가 쉽다. (의존 관계만 처리하니까) 아무튼 ClientA는 굉장히 결합도가 낮은 모듈이 되었다. Container가 결합도가 높은 모듈이어도 상관 없는 이유는 응집도가 매우 높은 모듈이라 의존관계 삑날거같으면 Container만 관리하면 되기 때문이다. 사실 저거에서도 인터페이스를 써서 더 결합도를 낮출 수 있지만, 요지는 객체 생성을 Client에게 숨김으로써 Client의 결합도가 낮아졌다는 것이다.  

13. 책임 연쇄 패턴 (chain - of - responsibility)  
https://kingchan223.tistory.com/306  

[설계 용어] 응집도(Cohesion)와 결합도(Coupling)  
https://medium.com/@jang.wangsu/%EC%84%A4%EA%B3%84-%EC%9A%A9%EC%96%B4-%EC%9D%91%EC%A7%91%EB%8F%84%EC%99%80-%EA%B2%B0%ED%95%A9%EB%8F%84-b5e2b7b210ff  

모듈(Module)이란? 결합도(Coupling)와 응집도(Cohesion)  
https://m.blog.naver.com/gluestuck/221899977072  

[소프트웨어 공학] SE 6 4 2 상세 설계 원리 - 모듈화 - 결합도 - 응집도 (Design Principles modularization coupling cohesion)  
https://youtu.be/mTZBDQj_Njo  

CodeSpitz78 1/ 루틴과 결합도-응집도 모델  
https://feel5ny.github.io/2018/08/30/OOP_03/  


    
    
