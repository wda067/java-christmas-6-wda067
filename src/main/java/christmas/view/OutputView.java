package christmas.view;

import static christmas.constants.Message.OUTPUT_AMOUNT_UNIT;
import static christmas.constants.Message.OUTPUT_BENEFIT_DETAILS_MESSAGE;
import static christmas.constants.Message.OUTPUT_BLANK;
import static christmas.constants.Message.OUTPUT_EVENT_BADGE_MESSAGE;
import static christmas.constants.Message.OUTPUT_GIFT_MENU_MESSAGE;
import static christmas.constants.Message.OUTPUT_NOTHING_MESSAGE;
import static christmas.constants.Message.OUTPUT_ONE_CHAMPAGNE;
import static christmas.constants.Message.OUTPUT_ORDER_MENU_MESSAGE;
import static christmas.constants.Message.OUTPUT_OVERVIEW_MESSAGE;
import static christmas.constants.Message.OUTPUT_QUANTITY_UNIT;
import static christmas.constants.Message.OUTPUT_TOTAL_AFTER_DISCOUNT_MESSAGE;
import static christmas.constants.Message.OUTPUT_TOTAL_BEFORE_DISCOUNT_MESSAGE;
import static christmas.constants.Message.OUTPUT_TOTAL_BENEFIT_AMOUNT_MESSAGE;
import static christmas.util.NumberFormatter.formatNumber;

import java.util.HashMap;

public class OutputView {


    public void printOverviewMessage(int day) {
        System.out.printf(OUTPUT_OVERVIEW_MESSAGE.getMessage(), day);
    }

    public void printOrderMenu(HashMap<String, Integer> orderMenu) {
        System.out.println(OUTPUT_ORDER_MENU_MESSAGE.getMessage());
        orderMenu.forEach((menu, count) ->
                System.out.println(menu + OUTPUT_BLANK.getMessage() +
                        count + OUTPUT_QUANTITY_UNIT.getMessage()));
    }

    public void printTotalOrderAmountBeforeDiscount(String price) {
        System.out.println(OUTPUT_TOTAL_BEFORE_DISCOUNT_MESSAGE.getMessage());
        System.out.println(price + OUTPUT_AMOUNT_UNIT.getMessage());
    }

    public void printGiftMenu(boolean isProvided) {
        System.out.println(OUTPUT_GIFT_MENU_MESSAGE.getMessage());
        if (isProvided) {
            System.out.println(OUTPUT_ONE_CHAMPAGNE.getMessage());
        }
        if (!isProvided) {
            System.out.println(OUTPUT_NOTHING_MESSAGE.getMessage());
        }
    }

    public void printBenefitDetails(HashMap<String, Integer> benefitDetails) {
        System.out.println(OUTPUT_BENEFIT_DETAILS_MESSAGE.getMessage());
        if (benefitDetails.isEmpty()) {
            System.out.println(OUTPUT_NOTHING_MESSAGE.getMessage());
            return;
        }
        benefitDetails.forEach((event, discount) ->
                System.out.println(event + formatNumber(discount) + OUTPUT_AMOUNT_UNIT.getMessage()));
    }

    public void printTotalBenefitAmount(int amount) {
        System.out.println(OUTPUT_TOTAL_BENEFIT_AMOUNT_MESSAGE.getMessage());
        System.out.println(formatNumber(amount) + OUTPUT_AMOUNT_UNIT.getMessage());
    }

    public void printExpectedTotalAfterDiscount(int total, int discount) {
        System.out.println(OUTPUT_TOTAL_AFTER_DISCOUNT_MESSAGE.getMessage());
        System.out.println(formatNumber(total + discount) + OUTPUT_AMOUNT_UNIT.getMessage());
    }

    public void printEventBadge(String badge) {
        System.out.println(OUTPUT_EVENT_BADGE_MESSAGE.getMessage());
        System.out.print(badge);
    }
}
