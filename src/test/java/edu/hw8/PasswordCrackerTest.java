package edu.hw8;

import edu.hw8.task3.PasswordCracker;
import edu.hw8.task3.PasswordGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PasswordCrackerTest {

    private static final int CORE = Runtime.getRuntime().availableProcessors();
    private static Stream<PasswordTestParams> passwordTestParams() {
        return Stream.of(
            new PasswordTestParams("abc", "900150983cd24fb0d6963f7d28e17f72", 3, CORE),
            new PasswordTestParams("abc", "900150983cd24fb0d6963f7d28e17f72", 5, CORE),
            new PasswordTestParams("123", "202cb962ac59075b964b07152d234b70", 3, CORE),
            new PasswordTestParams("admin", "21232f297a57a5a743894a0e4a801fc3", 6, CORE),
            new PasswordTestParams(null, "900150983cd24fb0d6963f7d28e17f72", 2, CORE)

        );
    }

    @ParameterizedTest
    @MethodSource("passwordTestParams")
    void testCrackPassword(PasswordTestParams params) {
        PasswordGenerator generator = new PasswordGenerator(params.maxLength);
        PasswordCracker cracker = new PasswordCracker(generator);
        String crackedPassword = cracker.crackPassword(params.targetHash, params.numberOfThreads);
        Assertions.assertEquals(params.expectedPassword, crackedPassword);
    }

    record PasswordTestParams(String expectedPassword, String targetHash, int maxLength, int numberOfThreads) {}
}
