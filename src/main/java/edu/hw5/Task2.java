package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.function.Predicate;

public class Task2 {

    private Task2() {
    }

    private static final int THIRTEEN = 13;

    public static List<LocalDate> findFridays13(int year) {
        LocalDate currentYear = LocalDate.ofYearDay(year, 1);
        LocalDate nextYear = LocalDate.of(year + 1, 1, 1);
        Predicate<LocalDate> isThirteen = date -> date.getDayOfMonth() == THIRTEEN;
        Predicate<LocalDate> isFriday = date -> date.getDayOfWeek() == DayOfWeek.FRIDAY;
        Predicate<LocalDate> predicate = date -> isThirteen.and(isFriday).test(date);

        return currentYear
                .datesUntil(nextYear)
                .filter(predicate)
                .toList();
    }

    public static LocalDate findNextFriday13(LocalDate currentDate) {
        return currentDate
            .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY))
            .withDayOfMonth(THIRTEEN);
    }
}
