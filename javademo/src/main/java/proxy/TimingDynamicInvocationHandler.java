package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import util.SimpleLoggers;

/**
 * ref : http://www.baeldung.com/java-dynamic-proxies
 *
 * @author zacconding
 * @Date 2018-03-01
 * @GitHub : https://github.com/zacscoding
 */
public class TimingDynamicInvocationHandler implements InvocationHandler {

    private Object target;

    private final Map<String, Method> methods = new HashMap<>();

    public TimingDynamicInvocationHandler(Object target) {
        this.target = target;
        for (Method method : target.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = methods.get(method.getName()).invoke(target, args);
        long elapsed = System.nanoTime() - start;
        SimpleLoggers.println("[## Executing {}::{}] args : {}, return : {}, elapsed : {} ns", target.getClass().getName(), method.getName(), Arrays.toString(args), result, elapsed);
        return result;
    }
}
