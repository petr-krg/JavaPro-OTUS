package krg.petr.otusru.logingframework;

import java.lang.reflect.Proxy;

public class LoggingProxy {
    private  static final ThreadLocal<Object> proxyHolder = new ThreadLocal<>();
    public static Object createLoggingProxy(Class<?> iface, Object clazz) {
        Object proxy = proxyHolder.get();
        if (!iface.isInstance(proxy)) {
            try {
                proxy = Proxy.newProxyInstance(
                        iface.getClassLoader(),
                        new Class<?>[]{iface},
                        new Handler(clazz)
                );
                proxyHolder.set(proxy);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return proxy;
    }

}
