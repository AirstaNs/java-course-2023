package edu.hw2.task4;

public final class CallerDetector {
    private CallerDetector() {
    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        StackTraceElement caller = stackTraceElements[1];
        return new CallingInfo(caller.getClassName(), caller.getMethodName());
    }
}
