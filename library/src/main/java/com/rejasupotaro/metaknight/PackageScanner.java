package com.rejasupotaro.metaknight;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

public class PackageScanner {
    private static final String TAG = PackageScanner.class.getSimpleName();

    public static List<Class<?>> searchSubClasses(Context context, Class targetClass) {
        String packageName = context.getPackageName();
        String sourcePath = context.getApplicationInfo().sourceDir;
        List<String> paths = new ArrayList<>();

        List<Class<?>> classes = new ArrayList<>();
        try {
            if (sourcePath != null && !(new File(sourcePath).isDirectory())) {
                DexFile dexfile = new DexFile(sourcePath);
                Enumeration<String> entries = dexfile.entries();

                while (entries.hasMoreElements()) {
                    paths.add(entries.nextElement());
                }
            } else { // Robolectric fallback
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                Enumeration<URL> resources = classLoader.getResources("");

                while (resources.hasMoreElements()) {
                    String path = resources.nextElement().getFile();
                    if (path.contains("bin") || path.contains("classes")) {
                        paths.add(path);
                    }
                }
            }

            for (String path : paths) {
                File file = new File(path);
                classes = searchSubClasses(file, packageName, context.getClassLoader(), targetClass, classes);
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        } finally {
            return classes;
        }
    }

    private static List<Class<?>> searchSubClasses(File path, String packageName, ClassLoader classLoader, Class targetClass, List<Class<?>> classes) {
        if (path.isDirectory()) {
            for (File file : path.listFiles()) {
                classes = searchSubClasses(file, packageName, classLoader, targetClass, classes);
            }
            return classes;
        } else {
            String className = path.getName();

            // Robolectric fallback
            if (!path.getPath().equals(className)) {
                className = path.getPath();

                if (className.endsWith(".class")) {
                    className = className.substring(0, className.length() - 6);
                } else {
                    return classes;
                }

                className = className.replace(System.getProperty("file.separator"), ".");

                int packageNameIndex = className.lastIndexOf(packageName);
                if (packageNameIndex < 0) {
                    return classes;
                }

                className = className.substring(packageNameIndex);
            }

            try {
                Class<?> discoveredClass = Class.forName(className, false, classLoader);
                if (ClassUtils.isSubclassOf(discoveredClass, targetClass)) {
                    classes.add(discoveredClass);
                }
            } catch (ClassNotFoundException e) {
                Log.e(TAG, e.getMessage(), e);
            } finally {
                return classes;
            }
        }
    }
}
