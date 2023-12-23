package edu.hw10.task2;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy<T> implements InvocationHandler {
    private final T target;
    private final Map<Method, Map<Integer, Object>> cache = new HashMap<>();

    private CacheProxy(T target) {
        this.target = target;
    }

    public static <T> T create(T target, Class<T> interfaceClass) {
        return (T) java.lang.reflect.Proxy.newProxyInstance(interfaceClass.getClassLoader(),
            new Class<?>[] {interfaceClass},
            new CacheProxy<>(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            Cache cacheAnnotation = method.getAnnotation(Cache.class);
            boolean persist = cacheAnnotation.persist();

            if (!cache.containsKey(method)) {
                cache.put(method, new HashMap<>());
            }

            int number = (int) args[0];

            if (cache.get(method).containsKey(number)) {
                return cache.get(method).get(number);
            }

            Object result;
            if (persist && loadFromCache(method, number)) {
                result = cache.get(method).get(number);
            } else {
                result = method.invoke(target, args);
                cache.get(method).put(number, result);

                if (persist) {
                    saveToCache(method, number, result);
                }
            }

            return result;
        } else {
            return method.invoke(target, args);
        }
    }

    private void saveToCache(Method method, int number, Object result) {
        String cacheFileName = getFileName(method, number);
        Path cacheFilePath = Paths.get(cacheFileName);

        try {
            Files.createDirectories(cacheFilePath.getParent());
            Files.write(cacheFilePath, String.valueOf(result).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFileName(Method method, int number) {
        final String cacheDirectory = "cache/";
        return "%s%s_%d.dat".formatted(cacheDirectory, method.getName(), number);
    }

    private boolean loadFromCache(Method method, int number) {
        String cacheFileName = getFileName(method, number);
        Path cacheFilePath = Paths.get(cacheFileName);

        if (Files.exists(cacheFilePath)) {
            try {
                String content = Files.readString(cacheFilePath);
                Object result = parseResult(content, method.getReturnType());
                cache.get(method).put(number, result);
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }

    private <R> R parseResult(String content, Class<R> returnType) {
        if (returnType == int.class || returnType == Integer.class) {
            return returnType.cast(Integer.valueOf(content));
        } else if (returnType == long.class || returnType == Long.class) {
            return returnType.cast(Long.valueOf(content));
        } else if (returnType == double.class || returnType == Double.class) {
            return returnType.cast(Double.valueOf(content));
        } else if (returnType == boolean.class || returnType == Boolean.class) {
            return returnType.cast(Boolean.valueOf(content));
        }

        throw new IllegalArgumentException("Unsupported return type: %s".formatted(returnType));
    }
}
