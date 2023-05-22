package krg.petr.otusru.logingframework;

import krg.petr.otusru.logingframework.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Handler implements InvocationHandler {
        private Object target;
        private final Map<String, Method> methodCache;

        public Handler(Object target) {
            this.target = target;
            methodCache = new HashMap<>();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method logMethod = getCachedMethod(method);

            if (logMethod != null) {
                if (logMethod.isAnnotationPresent(Log.class)) {
                    System.out.println("execute method: " + method.getName() + ", param: " + formatArguments(args));
                }
            }

            return method.invoke(target, args);
        }

        private Method getCachedMethod(Method method) {

            String methodName = method.getName();

            if (methodCache.containsKey(methodName)) {
                return methodCache.get(methodName);
            }

            Method[]  methods = target.getClass().getMethods();
            for (Method m : methods) {
                if (isMatchingMethod(m, method)) {
                    methodCache.put(methodName, m);
                    return m;
                }
            }

            return null;
        }

        private boolean isMatchingMethod(Method m1, Method m2) {

            if (!m1.getName().equals(m2.getName())) {
                return false;
            }

            Class<?>[] params1 = m1.getParameterTypes();
            Class<?>[] params2 = m2.getParameterTypes();

            if (params1.length != params2.length) {
                return false;
            }

            for (int i = 0; i < params1.length; i++) {
                if (!params1[i].equals(params2[i])) {
                    return false;
                }
            }

            return true;
        }

        private String formatArguments(Object[] args) {
            if (args == null || args.length == 0) {
                return "no arguments";
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                sb.append(args[i]);
                if (i < args.length - 1) {
                    sb.append(", ");
                }
            }

            return sb.toString();
        }
    }

