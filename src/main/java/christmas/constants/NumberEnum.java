package christmas.constants;

public enum NumberEnum {
    FIRST_DAY_OF_THE_DECEMBER(1),
    LAST_DAY_OF_THE_DECEMBER(31),
    MINIMUM_COUNT(1),
    MAXIMUM_COUNT(20),
    GIFT_EVENT_AMOUNT(120_000),
    MINIMUM_EVENT_AMOUNT(10_000),
    ZERO_VALUE(0),
    NEGATIVE_MULTIPLIER(-1),
    CHRISTMAS_DAY(25),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7),
    CURRENT_YEAR(2023),
    DEFAULT_DISCOUNT_AMOUNT(900),
    DISCOUNT_PER_DAY(100),
    DISCOUNT_PER_MENU(2023),
    SPECIAL_DISCOUNT_AMOUNT(1000),
    SANTA_BADGE_DISCOUNT_AMOUNT(20_000),
    TREE_BADGE_DISCOUNT_AMOUNT(10_000),
    STAR_BADGE_DISCOUNT_AMOUNT(50_000);


    private final int value;

    NumberEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
