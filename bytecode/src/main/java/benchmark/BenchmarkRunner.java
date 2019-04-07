package benchmark;

/**
 * https://www.baeldung.com/java-microbenchmark-harness
 */
public class BenchmarkRunner {
    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
//        MyBenchmark myBenchmark = new MyBenchmark();
//        myBenchmark.staticText();
//        myBenchmark.variable("");
//        myBenchmark.exception("text");
    }
}
