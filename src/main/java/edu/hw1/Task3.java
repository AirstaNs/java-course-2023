package edu.hw1;

import java.util.Objects;

public final class Task3 {

    private Task3() {
    }

    public static boolean isNestable(int[] internal, int[] external) {
        Objects.requireNonNull(internal);
        Objects.requireNonNull(external);

        if (internal.length == 0 || external.length == 0) {
            return false;
        }
        int[] minMaxInternal = findMinMax(internal);
        int[] minMaxExternal = findMinMax(external);
        return minMaxInternal[0] > minMaxExternal[0] && minMaxInternal[1] < minMaxExternal[1];
    }

    private static int[] findMinMax(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            } else if (arr[i] < min) {
                min = arr[i];
            }
        }
        return new int[] {min, max};
    }
}
