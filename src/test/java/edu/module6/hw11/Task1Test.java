package edu.module6.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    private static final String STRING = "Hello, ByteBuddy!";

    @NotNull
    private static DynamicType.Unloaded<Object> getUnloadedType() {
        return new ByteBuddy().subclass(Object.class)
                              .method(ElementMatchers.isToString())
                              .intercept(FixedValue.value(STRING))
                              .make();
    }

    @Test
    void testByteBodyClassCreation() {
        try (var unloadedType = getUnloadedType()) {
            var loaded = unloadedType.load(getClass().getClassLoader()).getLoaded();
            Object instance = loaded.getDeclaredConstructor().newInstance();
            assertEquals(STRING, instance.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
