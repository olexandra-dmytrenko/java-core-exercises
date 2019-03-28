package bytecode;

public interface iTextService {
    String staticText() throws InterruptedException;

    String variable(String variable);

    String exception(String text) throws RuntimeException;
}
