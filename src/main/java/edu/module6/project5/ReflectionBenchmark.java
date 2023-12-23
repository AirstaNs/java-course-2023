package edu.module6.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@SuppressWarnings({"UncommentedMain", "MagicNumber"})
@State(Scope.Thread)
public class ReflectionBenchmark {

    private Student student;
    private Method directMethod;
    private MethodHandle methodHandle;
    private Function<Student, String> lambdaFunction;

    @Setup
    public void setup() throws Throwable {
        final String methodName = "getName";
        this.student = new Student("student");

        this.directMethod = Student.class.getMethod(methodName);

        this.methodHandle =
            MethodHandles
            .lookup()
            .findVirtual(
                Student.class, methodName, MethodType.methodType(String.class)
            );

        this.lambdaFunction =
            (Function<Student, String>) LambdaMetafactory.metafactory(
                MethodHandles.lookup(),
                "apply",
                MethodType.methodType(Function.class),
                MethodType.methodType(Object.class, Object.class),
                methodHandle,
                methodHandle.type()
        ).getTarget().invokeExact();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void sourceAccess(Blackhole blackhole) {
        blackhole.consume(student.getName());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void reflectionAccess(Blackhole blackhole) throws Exception {
        blackhole.consume(directMethod.invoke(student));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void methodHandleAccess(Blackhole blackhole) throws Throwable {
        blackhole.consume(methodHandle.invoke(student));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void lambdaHandleAccess(Blackhole blackhole) {
        blackhole.consume(lambdaFunction.apply(student));
    }
/*
Benchmark                                 Mode  Cnt  Score   Error  Units
ReflectionBenchmark.sourceAccess        avgt       0,465          ns/op
ReflectionBenchmark.lambdaHandleAccess  avgt       0,647          ns/op
ReflectionBenchmark.methodHandleAccess  avgt       3,627          ns/op
ReflectionBenchmark.reflectionAccess    avgt       5,492          ns/op
*/

public static void main(String[] args) throws RunnerException {
        Options options =
            new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldDoGC(true)
            .shouldFailOnError(true)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .build();

        new Runner(options).run();
    }
}
