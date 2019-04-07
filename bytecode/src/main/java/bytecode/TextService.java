package bytecode;

import org.openjdk.jmh.annotations.Benchmark;

public class TextService implements iTextService {

    @Benchmark
    @Override
    public String staticText() {
        //System.out.println("My text");
        return "No ${static} text";
    }

    //@Benchmark
    @Override
    public String variable(String variable) {
        return variable;
    }

   // @Benchmark
    @Override
    public String exception(String text) throws RuntimeException {
        if (!text.equals("text"))
            throw new RuntimeException("Bad text");
        return text;
    }
}
