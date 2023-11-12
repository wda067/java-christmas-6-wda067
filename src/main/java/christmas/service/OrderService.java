package christmas.service;

import christmas.model.Menu;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderService {
    private final HashMap<String, Integer> map = new HashMap<>();
    private int total = 0;

    public HashMap<String, Integer> convertStringToCollection(String menuAndCount) {
        Pattern pattern = Pattern.compile("([가-힣]+)-(\\d+)");
        Matcher matcher = pattern.matcher(menuAndCount);

        while (matcher.find()) {
            map.put(matcher.group(1), Integer.valueOf(matcher.group(2)));
        }
        return map;
    }

    public void calculateTotal(HashMap<String, Integer> map) {
        for (Menu menu : Menu.values()) {
            if (map.containsKey(menu.getName())) {
                total += menu.getPrice() * map.get(menu.getName());
            }
        }
    }

    public int getTotal() {
        return total;
    }
}
