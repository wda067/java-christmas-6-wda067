package christmas.constants;

public enum Regex {
    NON_DIGIT_REGEX("\\D+"),
    MENU_AND_COUNT_REGEX("([가-힣]+)-(\\d+)"),
    COMMA_REGEX(",");
    private final String regex;

    Regex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
