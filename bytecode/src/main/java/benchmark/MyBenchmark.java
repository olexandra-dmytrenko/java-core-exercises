package benchmark;

public class MyBenchmark {

//    @Benchmark
//    @BenchmarkMode(Mode.AverageTime) //is executed for long time
    public String staticText() {
        //System.out.println("My text");
        return "No ${static} text";
    }

//    @Fork(value = 1, warmups = 2)
//    @BenchmarkMode(Mode.Throughput)
    public String staticText1() {
        //System.out.println("My text");
        return "No ${static} text";
    }

    //@Benchmark should be orgamised with parameters so is called in in ExecutionPlan
    public String variable(String variable) {
        return variable;
    }

    public String exception(String text) throws RuntimeException {
        if (!text.equals("text"))
            throw new RuntimeException("Bad text");
        return text;
    }
}
