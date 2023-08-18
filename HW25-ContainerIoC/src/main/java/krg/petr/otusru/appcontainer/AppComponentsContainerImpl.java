package krg.petr.otusru.appcontainer;

import krg.petr.otusru.appcontainer.api.AppComponent;
import krg.petr.otusru.appcontainer.api.AppComponentsContainer;
import krg.petr.otusru.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        try {
            Object configInstance = configClass.getDeclaredConstructor().newInstance();
            List<Method> methods = Arrays.stream(configClass.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(AppComponent.class))
                    .sorted(Comparator.comparingInt(m -> m.getAnnotation(AppComponent.class).order()))
                    .toList();
            for (Method method : methods) {
                String name = method.getAnnotation(AppComponent.class).name();
                if (appComponentsByName.containsKey(name)) {
                    throw new IllegalArgumentException("Component with name " + name + " already exists!");
                }
                var component = createComponent(configInstance, method);
                appComponentsByName.put(name, component);
                appComponents.add(component);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to process config: " + configClass.getName(), e);
        }
    }

    private Object createComponent(Object obj, Method method) {
        try {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Object[] args = Arrays.stream(parameterTypes)
                    .map(this::getAppComponent)
                    .toArray();
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        List<Object> components = appComponents.stream()
                .filter(component -> componentClass.isAssignableFrom(component.getClass()))
                .toList();

        if (components.size() > 1) {
            throw new IllegalStateException(String.format("Found multiple components of type %s!", componentClass));
        } else if (components.isEmpty()) {
            throw new NoSuchElementException(String.format("Component with type %s not found!", componentClass));
        }

        return (C) components.get(0);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) Optional.ofNullable(appComponentsByName.get(componentName))
                .orElseThrow(() -> new NoSuchElementException(String.format("Component with name %s not found!", componentName)));
    }
}
