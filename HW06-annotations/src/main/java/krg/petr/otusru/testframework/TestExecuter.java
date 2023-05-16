package krg.petr.otusru.testframework;

import krg.petr.otusru.testframework.annotations.After;
import krg.petr.otusru.testframework.annotations.Before;
import krg.petr.otusru.testframework.annotations.Test;

import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestExecuter {

    private final String moduleName;
    private final String packageName;
    private final String testsFolder;
    private Statistics testStatistics;
    private final Helper helper;

    public TestExecuter(String moduleName, String packageName, String testsFolder) {
        this.moduleName = moduleName;
        this.packageName = packageName;
        this.testsFolder = testsFolder;
        this.testStatistics = new Statistics();
        this.helper = new Helper();
    }

    public void testsExecute() {
        Path projectPath = Paths.get("").toAbsolutePath();
        Path modulePath = projectPath.resolve(moduleName);
        String testFolder = helper.getDirectory(modulePath.toString(), testsFolder);
        try {
            if (testFolder.isEmpty()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Class<?>> testClasses = helper.getClassesInDirectory(packageName, testFolder);

        for (Class<?> testClass : testClasses) {
            System.out.printf("Running test for %s \n", testClass.getName());

//            Object testObject;
//            try {
//                testObject = testClass.getConstructor().newInstance();
//            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException  e) {
//                e.printStackTrace();
//                continue;
//            }

            List<Method> beforeMethods = findMethodsAnnotatedWith(testClass, Before.class);
            List<Method> afterMethods = findMethodsAnnotatedWith(testClass, After.class);
            List<Method> testMethods = findMethodsAnnotatedWith(testClass, Test.class);

            testMethodsLoop: for (Method testMethod : testMethods) {

                Object testObject;
                try {
                    testObject = testClass.getConstructor().newInstance();
                } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException  e) {
                    e.printStackTrace();
                    continue;
                }

                for (Method beforeMethod : beforeMethods) {
                    testStatistics.testStarted();
                    try {
                        beforeMethod.invoke(testObject);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        testStatistics.testFailed();
                        System.out.printf("%s FAILED \n", beforeMethod.getName());
                        break testMethodsLoop;
                    }
                }

                try {
                    testMethod.invoke(testObject);
                    System.out.printf("%s PASSED \n", testMethod.getName());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    testStatistics.testFailed();
                    System.out.printf("%s FAILED \n", testMethod.getName());
                    e.printStackTrace();
                }

                for (Method afterMethod : afterMethods) {
                    try {
                        afterMethod.invoke(testObject);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.printf(testStatistics.getSummary());
    }

    private List<Method> findMethodsAnnotatedWith(Class<?> clazz, Class<? extends Annotation> annotation) {
        List<Method> annotatedMethod = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                annotatedMethod.add(method);
            }
        }
        return  annotatedMethod;
    }
}
