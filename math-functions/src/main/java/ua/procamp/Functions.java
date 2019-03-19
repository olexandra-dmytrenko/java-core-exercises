package ua.procamp;

public class Functions {
    /**
     * A static factory method that creates an integer function map with basic functions:
     * - abs (absolute value)
     * - sgn (signum function)
     * - increment
     * - decrement
     * - square
     *
     * @return an instance of {@link FunctionMap} that contains all listed functions
     */
    public static FunctionMap<Integer, Integer> intFunctionMap() {
        FunctionMap<Integer, Integer> intFunctionMap = new FunctionMap<>();

        intFunctionMap.addFunction("abs", Math::abs);
        intFunctionMap.addFunction("sgn", x -> (int) Math.signum(x));
        intFunctionMap.addFunction("increment", x -> x + 1);
        intFunctionMap.addFunction("decrement", x -> x - 1);
        intFunctionMap.addFunction("square", x -> x * x);
        // todo: add simple functions to the function map (abs, sng, increment, decrement, square)

        return intFunctionMap;
    }
}
