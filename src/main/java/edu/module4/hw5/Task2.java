package edu.module4.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Task2 {

    private static final int THIRTEEN = 13;

    private Task2() {
    }

    public static List<LocalDate> findFridays13(int year, int startMonth, int endMonth) {
        isValidInput(year, startMonth, endMonth);
        LocalDate startDate = LocalDate.of(year, startMonth, THIRTEEN);
        LocalDate endDate = LocalDate.of(year, endMonth, THIRTEEN).plusMonths(1);

        Predicate<LocalDate> isThirteen = date -> date.getDayOfMonth() == THIRTEEN;
        Predicate<LocalDate> isFriday = date -> date.getDayOfWeek() == DayOfWeek.FRIDAY;
        Predicate<LocalDate> predicate = date -> isThirteen.and(isFriday).test(date);

        int step = 1;
        return startDate
                    .datesUntil(endDate, Period.ofMonths(step))
                    .filter(predicate)
                    .toList();
    }

    public static LocalDate findNextFriday13(LocalDate currentDate) {
        Objects.requireNonNull(currentDate);
        LocalDate nextFriday13 = currentDate;
        do {
            nextFriday13 =
                nextFriday13
                    .with(TemporalAdjusters.firstDayOfNextMonth())
                    .withDayOfMonth(THIRTEEN);
        } while (nextFriday13.getDayOfWeek() != DayOfWeek.FRIDAY);
        return nextFriday13;
    }

    private static boolean isValidInput(int year, int startMonth, int endMonth) {
        final int maxMonth = 12;
        final int minMonth = 0;
        boolean isPositive = year > minMonth && startMonth > minMonth && endMonth > minMonth;
        if (!isPositive) {
            throw new IllegalArgumentException("Year and months must be positive values.");
        }
        boolean isMonthInRange = (startMonth <= maxMonth && endMonth <= maxMonth) && startMonth <= endMonth;
        if (!isMonthInRange) {
            throw new IllegalArgumentException(
                "Invalid month values. Month between 1 and 12, and start month <= end month");
        }
        return true;
    }
}
