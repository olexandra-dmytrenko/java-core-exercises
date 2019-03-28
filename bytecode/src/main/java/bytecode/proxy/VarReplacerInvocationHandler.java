package bytecode.proxy;

import bytecode.iTextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;


public class VarReplacerInvocationHandler implements InvocationHandler {

    private final Object classInstance;

    private static Logger LOGGER = LoggerFactory.getLogger(
            VarReplacerInvocationHandler.class);

    public VarReplacerInvocationHandler(Object classInstance) throws IllegalAccessException, InstantiationException {
        this.classInstance = classInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        LOGGER.info("Invoked method: {}", method.getName());
        if (Arrays.asList(proxy.getClass().getInterfaces()).contains(iTextService.class)) {
            System.out.println("Working with " + proxy.getClass().getName() + "." + method.getName());
            method.setAccessible(true);
            String result = (String) method.invoke(classInstance, args);
            RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
            Map<String, String> getenv = runtimeMxBean.getSystemProperties();
            for (String var : getenv.keySet()) {
                String fullVar = "${" + var + "}";
                if (result.contains(fullVar)) {
                    result = result.replace(fullVar, getenv.get(var));
                }
            }
            return result;
        }
        return "YES";
    }
}