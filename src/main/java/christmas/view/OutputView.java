package christmas.view;

import static christmas.util.NumberFormatter.formatNumber;

import java.util.HashMap;
import java.util.Map.Entry;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printOverviewMessage(int day) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n", day);
    }

    public void printOrderMenu(HashMap<String, Integer> orderMenu) {
        System.out.println("<주문 메뉴>");
        orderMenu.forEach((menu, count) ->
                System.out.println(menu + " " + count + "개"));
    }

    public void printTotalOrderAmountBeforeDiscount(String price) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(price + "원");
    }

    public void printGiftMenu(boolean isProvided) {
        System.out.println("\n<증정 메뉴>");
        if (isProvided) {
            System.out.println("샴페인 1개");
        }
        if (!isProvided) {
            System.out.println("없음");
        }
    }

    public void printBenefitDetails(HashMap<String, Integer> benefitDetails) {
        System.out.println("\n<혜택 내역>");
        if (benefitDetails.isEmpty()) {
            System.out.println("없음");
            return;
        }
        benefitDetails.forEach((event, discount) ->
                System.out.println(event + formatNumber(discount) + "원"));
    }

    public void printTotalBenefitAmount(int amount) {
        System.out.println("\n<총혜택 금액>");
        System.out.println(formatNumber(amount) + "원");
    }

    public void printExpectedTotalAfterDiscount(int total, int discount) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(formatNumber(total + discount) + "원");
    }

    public void printEventBadge(String badge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.print(badge);
    }
}
