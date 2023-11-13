package christmas.service;

import static christmas.constants.Badge.NOTHING;
import static christmas.constants.Badge.SANTA_BADGE;
import static christmas.constants.Badge.STAR_BADGE;
import static christmas.constants.Badge.TREE_BADGE;
import static christmas.constants.Message.OUTPUT_NOTHING_MESSAGE;
import static christmas.constants.Message.SANTA_BADGE;
import static christmas.constants.Message.STAR_BADGE;
import static christmas.constants.Message.TREE_BADGE;
import static christmas.constants.NumberEnum.CHRISTMAS_DAY;
import static christmas.constants.NumberEnum.CURRENT_YEAR;
import static christmas.constants.NumberEnum.DEFAULT_DISCOUNT_AMOUNT;
import static christmas.constants.NumberEnum.DISCOUNT_PER_DAY;
import static christmas.constants.NumberEnum.DISCOUNT_PER_MENU;
import static christmas.constants.NumberEnum.FRIDAY;
import static christmas.constants.NumberEnum.NEGATIVE_MULTIPLIER;
import static christmas.constants.NumberEnum.SANTA_BADGE_DISCOUNT_AMOUNT;
import static christmas.constants.NumberEnum.SATURDAY;
import static christmas.constants.NumberEnum.SPECIAL_DISCOUNT_AMOUNT;
import static christmas.constants.NumberEnum.STAR_BADGE_DISCOUNT_AMOUNT;
import static christmas.constants.NumberEnum.SUNDAY;
import static christmas.constants.NumberEnum.TREE_BADGE_DISCOUNT_AMOUNT;
import static christmas.constants.NumberEnum.ZERO_VALUE;
import static java.time.Month.DECEMBER;

import christmas.model.Menu;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;

public class EventService {

    public int calculateXmasDiscount(int day) {
        if (day <= CHRISTMAS_DAY.getValue()) {
            return NEGATIVE_MULTIPLIER.getValue() *
                    (DEFAULT_DISCOUNT_AMOUNT.getValue() + (day) *
                            DISCOUNT_PER_DAY.getValue());
        }
        return ZERO_VALUE.getValue();
    }

    public int calculateWeekdayDiscount(HashMap<String, Integer> map) {
        long dessertCount = map.keySet().stream()
                .filter(name -> Menu.getDessertList().contains(name))
                .mapToInt(map::get)
                .sum();
        return (int) (NEGATIVE_MULTIPLIER.getValue() * (dessertCount * DISCOUNT_PER_MENU.getValue()));
    }

    public int calculateWeekendDiscount(HashMap<String, Integer> map) {
        long mainCount = map.keySet().stream()
                .filter(name -> Menu.getMainList().contains(name))
                .mapToInt(map::get)
                .sum();
        return (int) (NEGATIVE_MULTIPLIER.getValue() * (mainCount * DISCOUNT_PER_MENU.getValue()));
    }

    public boolean isWeekend(int day) {
        LocalDate date = LocalDate.of(CURRENT_YEAR.getValue(), DECEMBER.getValue(), day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getValue() == FRIDAY.getValue() ||
                dayOfWeek.getValue() == SATURDAY.getValue();
    }

    public int calculateSpecialDiscount(int day) {
        if (hasStarInCalendar(day)) {
            return NEGATIVE_MULTIPLIER.getValue() * SPECIAL_DISCOUNT_AMOUNT.getValue();
        }
        return ZERO_VALUE.getValue();
    }

    public boolean hasStarInCalendar(int day) {
        LocalDate date = LocalDate.of(CURRENT_YEAR.getValue(), DECEMBER.getValue(), day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getValue() == SUNDAY.getValue() || day == CHRISTMAS_DAY.getValue();
    }

    public String determineBadge(int discountAmount) {
        if (discountAmount >= SANTA_BADGE_DISCOUNT_AMOUNT.getValue()) {
            return SANTA_BADGE.getBadge();
        }
        if (discountAmount >= TREE_BADGE_DISCOUNT_AMOUNT.getValue()) {
            return TREE_BADGE.getBadge();
        }
        if (discountAmount >= STAR_BADGE_DISCOUNT_AMOUNT.getValue()) {
            return STAR_BADGE.getBadge();
        }
        return NOTHING.getBadge();
    }
}
