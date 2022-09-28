package _02_;

public class PrintRequestHandler extends RequestHandler {
    @Override
    public void handler(Request request) {
        System.out.println(request.getBody());
    }
}
