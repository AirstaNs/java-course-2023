package edu.module6.hw10.task1;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomObjectGeneratorTests {
    private final RandomObjectGenerator rog = new RandomObjectGenerator();

    @Test
    void testCreateObjectWithConstructorAndAnnotationConstraints() throws ReflectiveOperationException {
        var obj = rog.nextObject(ClassMultipleConstructors.class);

        assertThat(obj.getStringValue()).isNotNull();
        assertThat(obj.getIntValue()).isGreaterThanOrEqualTo(10);
    }

    @Test
    void testCreateObjectWithStaticMethodAndConstraints() throws ReflectiveOperationException {
        var obj = rog.nextObject(ClassFactory.class, "create");

        assertThat(obj.getStringValue()).isNotNull();
        assertThat(obj.getIntValue()).isGreaterThanOrEqualTo(10);
    }

    @Test
    void testCreateRecordObjectWithValidation() throws ReflectiveOperationException {
        var record = rog.nextObject(Record.class);

        assertThat(record.stringValue()).isNotNull();
        assertThat(record.intValue()).isGreaterThanOrEqualTo(10);
    }

    @Test
    void testExceptionThrownForNonExistentMethod() {
        assertThrows(NoSuchElementException.class, () -> rog.nextObject(Record.class, "someUnknownMethod"));
    }
}
