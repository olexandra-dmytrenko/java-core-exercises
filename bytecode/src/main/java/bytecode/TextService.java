package bytecode;

public class TextService {

    public String staticText() throws InterruptedException {
        //System.out.println("My text");
        return "No static text";
    }

    public String variable(String variable) {
        return variable;
    }

    public String exception(String text) throws RuntimeException {
        if (!text.equals("text"))
            throw new RuntimeException("Bad text");
        return text;
    }
}
