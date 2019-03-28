package bytecode;

public class TextService implements iTextService {

    @Override
    public String staticText() throws InterruptedException {
        //System.out.println("My text");
        return "No ${static} text";
    }

    @Override
    public String variable(String variable) {
        return variable;
    }

    @Override
    public String exception(String text) throws RuntimeException {
        if (!text.equals("text"))
            throw new RuntimeException("Bad text");
        return text;
    }
}
