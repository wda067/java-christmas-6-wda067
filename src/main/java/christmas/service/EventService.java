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
}
