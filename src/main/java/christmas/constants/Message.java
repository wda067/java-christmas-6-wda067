package christmas.constants;

public enum Message {
    INPUT_VISIT_DATE_MESSAGE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INPUT_MENU_AND_COUNT("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    OUTPUT_WELCOME_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    OUTPUT_OVERVIEW_MESSAGE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n"),
    OUTPUT_ORDER_MENU_MESSAGE("<주문 메뉴>"),
    OUTPUT_TOTAL_BEFORE_DISCOUNT_MESSAGE("\n<할인 전 총주문 금액>"),
    OUTPUT_GIFT_MENU_MESSAGE("\n<증정 메뉴>"),
    OUTPUT_ONE_CHAMPAGNE("샴페인 1개"),
    OUTPUT_NOTHING_MESSAGE("없음"),
    OUTPUT_BENEFIT_DETAILS_MESSAGE("\n<혜택 내역>"),
    OUTPUT_TOTAL_BENEFIT_AMOUNT_MESSAGE("\n<총혜택 금액>"),
    OUTPUT_TOTAL_AFTER_DISCOUNT_MESSAGE("\n<할인 후 예상 결제 금액>"),
    OUTPUT_EVENT_BADGE_MESSAGE("\n<12월 이벤트 배지>"),
    OUTPUT_BLANK(" "),
    OUTPUT_QUANTITY_UNIT("개"),
    OUTPUT_AMOUNT_UNIT("원");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
