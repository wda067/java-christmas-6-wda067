package christmas.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Menu {

    BUTTON_MUSHROOM_SOUP("appetizer", "양송이수프", 6000),
    TAPAS("appetizer", "타파스", 5500),
    CAESAR_SALAD("appetizer", "시저샐러드", 8000),

    T_BONE_STEAK("main", "티본스테이크", 55000),
    BBQ_RIBS("main", "바비큐립", 54000),
    SEAFOOD_PASTA("main", "해산물파스타", 35000),
    CHRISTMAS_PASTA("main", "크리스마스파스타", 25000),

    CHOCO_CAKE("dessert", "초코케이크", 15000),
    ICE_CREAM("dessert", "아이스크림", 5000),

    ZERO_COLA("beverage", "제로콜라", 3000),
    RED_WINE("beverage", "레드와인", 60000),
    CHAMPAGNE("beverage", "샴페인", 25000);

    private final String menu;
    private final String name;
    private final int price;

    Menu(String menu, String name, int price) {
        this.menu = menu;
        this.name = name;
        this.price = price;
    }

    public String getMenu() {
        return menu;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static List<String> getDessertList() {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getMenu().equals("dessert"))
                .map(Menu::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getMainList() {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getMenu().equals("main"))
                .map(Menu::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getNameList() {
        return Arrays.stream(Menu.values())
                .map(Menu::getName)
                .collect(Collectors.toList());
    }
}
