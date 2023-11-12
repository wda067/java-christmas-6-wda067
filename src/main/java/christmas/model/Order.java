package christmas.model;

import java.util.HashMap;

public class Order {
    private final HashMap<String, Integer> map;

    public Order(HashMap<String, Integer> map) {
        this.map = map;
    }

    public int getCount() {
        int count = 0;
        for (Integer value : map.values()) {
            count += value;
        }
        return count;
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }
}
