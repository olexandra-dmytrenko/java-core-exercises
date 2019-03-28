package bytecode.proxy;

import bytecode.TextService;
import bytecode.iTextService;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;

public class VarReplacerInvocationHandlerTest {

    /*
    To run the test add  -Dport="8080" to VM options or use
    java -Dport="8080" /Users/olexandra/IdeaProjects/java-core-exercises/bytecode/src/test/java/bytecode/proxy/VarReplacerInvocationHandlerTest.java
     */

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        iTextService proxyInstance = (iTextService) Proxy.newProxyInstance(
                VarReplacerInvocationHandlerTest.class.getClassLoader(),
                new Class[]{iTextService.class},
                new VarReplacerInvocationHandler(new TextService()));
        System.out.println(proxyInstance.variable("my port is ${port}"));
    }

    @Test
    public void testProxy() throws InterruptedException, InstantiationException, IllegalAccessException {
        iTextService proxyInstance = (iTextService) Proxy.newProxyInstance(
                VarReplacerInvocationHandlerTest.class.getClassLoader(),
                new Class[]{iTextService.class},
                new VarReplacerInvocationHandler(new TextService()));
        assertEquals("my port is 8080", proxyInstance.variable("my port is ${port}"));

    }
}