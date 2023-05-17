package krg.petr.otusru.testframework;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Helper {

    public String getDirectory(String pathModule, String packageName) {
        String result = "";
        File modulePath = new File(pathModule);
        File testsDirectory = findDirectory(modulePath, packageName);
        if (testsDirectory != null) {
            result = testsDirectory.getAbsolutePath();
        }
        return result;
    }

    private File findDirectory(File directory, String directoryName) {
        if (directory.isDirectory()) {
            if (directory.getName().equals(directoryName)) {
                return directory;
            } else {
                for (File file : Objects.requireNonNull(directory.listFiles())) {
                    if (file.isDirectory()) {
                        File result = findDirectory(file, directoryName);
                        if (result != null) {
                            return result;
                        }
                    }
                }
            }
        }
        return null;
    }

    public List<Class<?>> getClassesInDirectory(String packageName, String directoryPath) {
        List<Class<?>> classes = new ArrayList<>();

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".class")) {
                try {
                    String className = findClassName(packageName, file);
                    Class<?> clazz = Class.forName(className);
                    classes.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return classes;
    }

    private String findPackage(String fileName) {

        return "";
    }

    private String findClassName(String rootPackageName, File file) {
        String packageName = file.getParentFile().getName();
        String result = file.getName().replace(".class", "");
        //result = result.replace('/','.');
        return rootPackageName + "." + packageName + "." + result;
    }
}
