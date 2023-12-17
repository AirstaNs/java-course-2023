package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @NotNull
    private static DynamicType.Unloaded<ArithmeticUtils> getUnloaded() {
        return new ByteBuddy().redefine(ArithmeticUtils.class)
                              .method(ElementMatchers.named("sum"))
                              .intercept(MethodDelegation.to(ArithmeticUtilsModified.class))
                              .make();
    }

    @Test
    public void testByteBuddyDynamicClassEditor() {
        ByteBuddyAgent.install();
        try (var unloaded = getUnloaded()) {
            unloaded.load(ClassLoader.getSystemClassLoader(), ClassReloadingStrategy.fromInstalledAgent()).getLoaded();
        } catch (Exception ignored) {}
        int actual = ArithmeticUtils.sum(4, 4);
        assertEquals(16, actual);
    }

    private static final class ArithmeticUtilsModified {
        public static int mult(int a, int b) {
            return a * b;
        }
    }
}
