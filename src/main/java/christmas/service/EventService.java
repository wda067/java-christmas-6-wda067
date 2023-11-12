package christmas.service;

import christmas.model.Menu;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;

public class EventService {

    public int calculateXmasDiscount(int day) {
        return -1 * (1000 + (day - 1) * 100);
    }

    public int calculateWeekdayDiscount(HashMap<String, Integer> map) {
        long dessertCount = map.keySet().stream()
                .filter(name -> Menu.getDessertList().contains(name))
                .mapToInt(map::get)
                .sum();
        return (int) (-1 * (dessertCount * 2023));
    }

    public int calculateWeekendDiscount(HashMap<String, Integer> map) {
        long mainCount = map.keySet().stream()
                .filter(name -> Menu.getMainList().contains(name))
                .mapToInt(map::get)
                .sum();
        return (int) (-1 * (mainCount * 2023));
    }

    public boolean isWeekend(int day) {
        LocalDate date = LocalDate.of(2023, 12, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getValue() == 5 || dayOfWeek.getValue() == 6;
    }

    public int calculateSpecialDiscount(int day) {
        if (hasStarInCalendar(day)) {
            return -1 * 1000;
        }
        return 0;
    }

    public boolean hasStarInCalendar(int day) {
        LocalDate date = LocalDate.of(2023, 12, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getValue() == 7 || day == 25;
    }

    public String determineBadge(int discountAmount) {
        if (discountAmount >= 20_000) {
            return "산타";
        }
        if (discountAmount >= 10_000) {
            return "트리";
        }
        return "별";
    }
}
