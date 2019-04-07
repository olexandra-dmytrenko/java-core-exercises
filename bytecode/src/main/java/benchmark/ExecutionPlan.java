package benchmark;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ExecutionPlan {


    @Param({ "100", "200", "300", "500", "1000" })
    public int iterations;

    public MyBenchmark myBenchmark;

    public String inputArg = "TestStr";

    @Setup(Level.Invocation)
    public void setUp() {
        myBenchmark = new MyBenchmark();
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void benchmarkVariableInput(ExecutionPlan plan) {

        for (int i = plan.iterations; i > 0; i--) {
            plan.myBenchmark.variable(inputArg);
        }
    }
}
