package edu.hw5;

public class Task8 {

    private Task8() {
    }

    // нечетной длины
    public static boolean task1(String input) {
        return input.matches("([01]{2})*[01]");
    }

    //    начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    public static boolean task2(String input) {
        return input.matches("1[01](?:[01]{2})*") || input.matches("0(?:[01]{2})*");
    }

    //    количество 0 кратно 3
    public static boolean task3(String input) {
        return input.matches("^(1*0){3}1*$");
    }

    //    любая строка, кроме 11 или 111
    public static boolean task4(String input) {
        return input.matches("^(?!(11|111)$)[01]*$");
    }

    //    каждый нечетный символ равен 1
    public static boolean task5(String input) {
        return input.matches("(?:1[10]?)+");
    }

    // содержит не менее двух 0 и не более одной 1
    public static boolean task6(String input) {
        return input.matches("^(?=.*0.*0)(?!.*1.*1)[01]*$");
    }

    //нет последовательных 1
    public static boolean task7(String input) {
        return !input.matches("[01]*11[01]*");
    }
}
