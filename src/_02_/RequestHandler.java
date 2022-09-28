package _02_;

public class RequestHandler {
    public void handler(Request request) {
        System.out.println(request.getBody());
    }
}
