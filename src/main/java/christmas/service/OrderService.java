package christmas.service;

import christmas.model.Menu;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderService {
    private final HashMap<String, Integer> map = new HashMap<>();
    private int total;
    private boolean isDuplicateName;
    private boolean isZeroValue;

    private static boolean isVisitDateWrongRange(String visitDate) {
        return Integer.parseInt(visitDate) < 1 || Integer.parseInt(visitDate) > 31;
    }

    public void validateVisitDate(String visitDate) {
        if (isVisitDateNonDigit(visitDate)) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
        if (isVisitDateWrongRange(visitDate)) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public boolean isVisitDateNonDigit(String visitDate) {
        return Pattern.compile("\\D+").matcher(visitDate).matches();
    }

    public HashMap<String, Integer> convertStringToCollection(String menuAndCount) {
        Pattern pattern = Pattern.compile("([가-힣]+)-(\\d+)");
        Matcher matcher = pattern.matcher(menuAndCount);
        while (matcher.find()) {
            String menuName = matcher.group(1);
            int menuCount = Integer.parseInt(matcher.group(2));
            if (map.containsKey(menuName)) {
                isDuplicateName = true;
            }
            if (menuCount < 1) {
                isZeroValue = true;
            }
            map.put(menuName, menuCount);
        }
        return map;
    }
}
