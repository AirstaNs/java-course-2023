package edu.module6.hw10.task1;

import edu.module6.hw10.task1.generator.Generator;
import edu.module6.hw10.task1.generator.IntGenerator;
import edu.module6.hw10.task1.generator.StringGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;

public class RandomObjectGenerator {
    private final Generator generator;

    public RandomObjectGenerator() {
        this.generator = Generator.handle(new IntGenerator(), new StringGenerator());
    }

    public RandomObjectGenerator(Generator sequence) {
        this.generator = sequence;
    }

    public <T> T nextObject(Class<T> entityClass) throws ReflectiveOperationException {
        var selectedConstructor = Arrays.stream(entityClass.getDeclaredConstructors())
            .max(Comparator.comparingInt(Constructor::getParameterCount))
            .orElseThrow();
        selectedConstructor.setAccessible(true);
        return (T) selectedConstructor.newInstance(prepareArguments(selectedConstructor));
    }

    public <T> T nextObject(
        Class<T> entityClass, String factoryMethodName
    ) throws ReflectiveOperationException {
        if (factoryMethodName == null) {
            return nextObject(entityClass);
        }

        Method factoryMethod = Arrays.stream(entityClass.getDeclaredMethods())
            .filter(method -> method.getName().equals(factoryMethodName)).findFirst().orElseThrow();

        factoryMethod.setAccessible(true);
        return (T) factoryMethod.invoke(null, prepareArguments(factoryMethod));
    }

    private Object[] prepareArguments(Executable executable) {
        Parameter[] params = executable.getParameters();
        Object[] arguments = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            arguments[i] = generator.generate(params[i]);
        }

        return arguments;
    }
}
