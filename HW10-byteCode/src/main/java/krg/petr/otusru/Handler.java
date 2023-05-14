package krg.petr.otusru;

import krg.petr.otusru.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Handler implements InvocationHandler {
    private Object target;

    public Handler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method logMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());

        if (logMethod.isAnnotationPresent(Log.class)) {
            System.out.println("execute method: " + method.getName() + ", param: " + Arrays.toString(args));
        }

        return method.invoke(target, args);
    }
}
