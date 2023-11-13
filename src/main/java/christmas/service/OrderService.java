package christmas.service;

import static christmas.constants.ExceptionMessage.EXCEPTION_PREFIX;
import static christmas.constants.ExceptionMessage.INVALID_DATE;
import static christmas.constants.ExceptionMessage.INVALID_ORDER;
import static christmas.constants.NumberEnum.FIRST_DAY_OF_THE_DECEMBER;
import static christmas.constants.NumberEnum.GIFT_EVENT_AMOUNT;
import static christmas.constants.NumberEnum.LAST_DAY_OF_THE_DECEMBER;
import static christmas.constants.NumberEnum.MAXIMUM_COUNT;
import static christmas.constants.NumberEnum.MINIMUM_COUNT;
import static christmas.constants.Regex.COMMA_REGEX;
import static christmas.constants.Regex.MENU_AND_COUNT_REGEX;
import static christmas.constants.Regex.NON_DIGIT_REGEX;

import christmas.model.Menu;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderService {
    private HashMap<String, Integer> orderedMenu;
    private int total;
    private boolean isDuplicateName;
    private boolean isCountZeroValue;

    public void validateVisitDate(String visitDate) {
        if (isVisitDateNonDigit(visitDate)) {
            throw new IllegalArgumentException(EXCEPTION_PREFIX.getMessage() + INVALID_DATE.getMessage());
        }
        if (isVisitDateWrongRange(visitDate)) {
            throw new IllegalArgumentException(EXCEPTION_PREFIX.getMessage() + INVALID_DATE.getMessage());
        }
    }

    private boolean isVisitDateNonDigit(String visitDate) {
        return Pattern.compile(NON_DIGIT_REGEX.getRegex()).matcher(visitDate).matches();
    }

    private boolean isVisitDateWrongRange(String visitDate) {
        int day = Integer.parseInt(visitDate);
        return day < FIRST_DAY_OF_THE_DECEMBER.getValue() || day > LAST_DAY_OF_THE_DECEMBER.getValue();
    }

    public void initializeVariables() {
        isDuplicateName = false;
        isCountZeroValue = false;
        orderedMenu = new HashMap<>();
    }

    public HashMap<String, Integer> convertStringToCollection(String menuAndCount) {
        Pattern pattern = Pattern.compile(MENU_AND_COUNT_REGEX.getRegex());
        Matcher matcher = pattern.matcher(menuAndCount);
        while (matcher.find()) {
            String menuName = matcher.group(1);
            int menuCount = Integer.parseInt(matcher.group(2));
            if (orderedMenu.containsKey(menuName)) {
                isDuplicateName = true;
            }
            if (menuCount < MINIMUM_COUNT.getValue()) {
                isCountZeroValue = true;
            }
            orderedMenu.put(menuName, menuCount);
        }
        return orderedMenu;
    }

    public void validateMenuAndCount(String menuAndCount) {
        HashSet<String> set = new HashSet<>(List.of(menuAndCount.split(COMMA_REGEX.getRegex())));
        if (isIncorrectFormat()) {
            throw new IllegalArgumentException(EXCEPTION_PREFIX.getMessage() + INVALID_ORDER.getMessage());
        }
        if (isDividedByComma(set)) {
            throw new IllegalArgumentException(EXCEPTION_PREFIX.getMessage() + INVALID_ORDER.getMessage());
        }
        if (isDuplicateName) {
            throw new IllegalArgumentException(EXCEPTION_PREFIX.getMessage() + INVALID_ORDER.getMessage());
        }
        if (isCountZeroValue) {
            throw new IllegalArgumentException(EXCEPTION_PREFIX.getMessage() + INVALID_ORDER.getMessage());
        }
        if (isMenuNotExists()) {
            throw new IllegalArgumentException(EXCEPTION_PREFIX.getMessage() + INVALID_ORDER.getMessage());
        }
        if (isOrderOnlyBeverage()) {
            throw new IllegalArgumentException(EXCEPTION_PREFIX.getMessage() + INVALID_ORDER.getMessage());
        }
        if (isOrderCountOverTwenty()) {
            throw new IllegalArgumentException(EXCEPTION_PREFIX.getMessage() + INVALID_ORDER.getMessage());
        }
    }

    private boolean isIncorrectFormat() {
        return orderedMenu.isEmpty();
    }

    private boolean isDividedByComma(HashSet<String> set) {
        return orderedMenu.size() != set.size();
    }

    private boolean isMenuNotExists() {
        return !new HashSet<>(Menu.getNameList()).containsAll(orderedMenu.keySet());
    }

    private boolean isOrderOnlyBeverage() {
        return new HashSet<>(Menu.getBeverageList()).containsAll(orderedMenu.keySet());
    }

    private boolean isOrderCountOverTwenty() {
        int count = orderedMenu.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        return count > MAXIMUM_COUNT.getValue();
    }

    public void calculateTotal(HashMap<String, Integer> map) {
        total = Arrays.stream(Menu.values())
                .filter(menu -> map.containsKey(menu.getName()))
                .mapToInt(menu -> menu.getPrice() * map.get(menu.getName()))
                .sum();
    }

    public int getTotal() {
        return total;
    }

    public boolean isChampagneProvided(int total) {
        return total >= GIFT_EVENT_AMOUNT.getValue();
    }
}
