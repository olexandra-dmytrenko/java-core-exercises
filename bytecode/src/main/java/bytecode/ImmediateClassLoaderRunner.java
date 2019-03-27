package bytecode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ImmediateClassLoaderRunner {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, InterruptedException {
        String progClass = "bytecode/TextService";//args[0];
        //String progArgs[] = new String[args.length - 1];
        //System.arraycopy(args, 1, progArgs, 0, progArgs.length);
//TODO: find out why it doesn't show the effect on non static methods
        while (true) {
            ImmediateClassLoader loader = new ImmediateClassLoader(ImmediateClassLoaderRunner.class.getClassLoader());
            Class<?> testServiceClass = loader.findClass(progClass);
            Object testServiceNewInstance = testServiceClass.newInstance();
            Method staticMethod = testServiceClass.getMethod("staticText");
            Method getterMethod = testServiceClass.getMethod("variable", String.class);
            Method exceptionMethod = testServiceClass.getMethod("exception", String.class);
            exceptionMethod.setAccessible(true);
      //      String exceptionMethodResult = (String) exceptionMethod.invoke(testServiceNewInstance, "1text1");
       //     System.out.println(exceptionMethodResult);
            getterMethod.setAccessible(true);
            String getterResult = (String) getterMethod.invoke(testServiceNewInstance, " Fine");
            System.out.println(getterResult);
            String staticInvokeResult = (String) staticMethod.invoke(testServiceNewInstance);
            System.out.println(staticInvokeResult);
            Thread.sleep(1000);
        }

//        ImmediateClassLoader ccl = new ImmediateClassLoader(ImmediateClassLoaderRunner.class.getClassLoader());
//        Class clas = ccl.loadClass(progClass, true);
//        Class mainArgType[] = { (new String[0]).getClass() };
//        Method method = clas.getMethod("staticText", mainArgType);
//        Object argsArray[] = null; //{ progArgs };
//        method.invoke(null, argsArray);
//
//        // Below method is used to check that the Foo is getting loaded
//        // by our custom class loader i.e CCLoader
//        Method printCL = clas.getMethod("printCL", null);
//        printCL.invoke(null, new Object[0]);
    }
}
