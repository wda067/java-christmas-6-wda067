package christmas.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Menu {
    BUTTON_MUSHROOM_SOUP("애피타이저", "양송이수프", 6000),
    TAPAS("애피타이저", "타파스", 5500),
    CAESAR_SALAD("애피타이저", "시저샐러드", 8000),
    T_BONE_STEAK("메인", "티본스테이크", 55000),
    BBQ_RIBS("메인", "바비큐립", 54000),
    SEAFOOD_PASTA("메인", "해산물파스타", 35000),
    CHRISTMAS_PASTA("메인", "크리스마스파스타", 25000),
    CHOCO_CAKE("디저트", "초코케이크", 15000),
    ICE_CREAM("디저트", "아이스크림", 5000),
    ZERO_COLA("음료", "제로콜라", 3000),
    RED_WINE("음료", "레드와인", 60000),
    CHAMPAGNE("음료", "샴페인", 25000);

    private final String menu;
    private final String name;
    private final int price;

    Menu(String menu, String name, int price) {
        this.menu = menu;
        this.name = name;
        this.price = price;
    }

    public static List<String> getNameList() {
        return Arrays.stream(Menu.values())
                .map(Menu::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getDessertList() {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getMenu().equals("디저트"))
                .map(Menu::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getMainList() {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getMenu().equals("메인"))
                .map(Menu::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getBeverageList() {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getMenu().equals("음료"))
                .map(Menu::getName)
                .collect(Collectors.toList());
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
}
