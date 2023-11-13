package christmas.constants;

public enum Badge {

    SANTA_BADGE("산타"),
    TREE_BADGE("트리"),
    STAR_BADGE("별"),
    NOTHING("없음");

    private final String badge;

    Badge(String badge) {
        this.badge = badge;
    }

    public String getBadge() {
        return badge;
    }
}