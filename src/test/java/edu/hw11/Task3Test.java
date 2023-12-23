package edu.hw11;

import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    @Test
    public void shouldCreateNewClassWhichCanCalculateFibNumbers() {
        int n = 15;
        int expected = 610;
        try (var unloaded = createClass()) {
            Class<?> loaded = unloaded.load(getClass().getClassLoader()).getLoaded();
            long actual = (long) loaded.getDeclaredMethod("fib", int.class)
                                       .invoke(loaded.getDeclaredConstructor().newInstance(), n);
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private DynamicType.Unloaded<Object> createClass() {
        return new ByteBuddy().subclass(Object.class)
                              .name("FibonacciCalculator")
                              .defineMethod("fib", long.class, Modifier.PUBLIC)
                              .withParameters(int.class)
                              .intercept(getImplementation())
                              .make();
    }

    private Implementation getImplementation() {
        return new Implementation() {
            @Override
            public ByteCodeAppender appender(Target target) {
                return (mv, context, methodDescription) -> {
                    Label l1 = new Label();
                    // x < 2
                    mv.visitCode();
                    mv.visitVarInsn(Opcodes.ILOAD, 1);
                    mv.visitInsn(Opcodes.ICONST_2);
                    mv.visitJumpInsn(Opcodes.IF_ICMPGE, l1);
                    mv.visitVarInsn(Opcodes.ILOAD, 1);
                    mv.visitInsn(Opcodes.I2L);
                    mv.visitInsn(Opcodes.LRETURN);

                    // fib(n - 1) + fib(n - 2);
                    mv.visitLabel(l1);
                    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                    mv.visitVarInsn(Opcodes.ALOAD, 0);
                    mv.visitVarInsn(Opcodes.ILOAD, 1);
                    mv.visitInsn(Opcodes.ICONST_1);
                    mv.visitInsn(Opcodes.ISUB);
                    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "FibonacciCalculator", "fib", "(I)J");
                    mv.visitVarInsn(Opcodes.ALOAD, 0);
                    mv.visitVarInsn(Opcodes.ILOAD, 1);
                    mv.visitInsn(Opcodes.ICONST_2);
                    mv.visitInsn(Opcodes.ISUB);
                    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "FibonacciCalculator", "fib", "(I)J");
                    mv.visitInsn(Opcodes.LADD);
                    mv.visitInsn(Opcodes.LRETURN);
                    mv.visitEnd();
                    return new ByteCodeAppender.Size(5, 2);
                };
            }

            @Override
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }
        };
    }
}
