package edu.hw2.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallerDetectorTests {

    @Test
    @DisplayName("Проверка правильности определения имени класса и метода")
    public void testDetectCaller() {
        CallingInfo info = methodForDetection();
        assertEquals(this.getClass().getName(), info.className());
        assertEquals("methodForDetection", info.methodName());
    }

    @Test
    @DisplayName("Проверка правильности определения при вызове из другого метода")
    public void testDetectCallerFromDifferentMethod() {
        CallingInfo info = anotherMethodForDetection();
        assertEquals(this.getClass().getName(), info.className());
        assertEquals("methodForDetection", info.methodName());
    }

    private CallingInfo methodForDetection() {
        return CallerDetector.callingInfo();
    }

    private CallingInfo anotherMethodForDetection() {
        return methodForDetection();
    }

    @Test
    @DisplayName("Проверка правильности определения при прямом вызове")
    public void testDirectCall() {
        CallingInfo info = CallerDetector.callingInfo();
        assertEquals(this.getClass().getName(), info.className());
        assertEquals("testDirectCall", info.methodName());
    }
}
