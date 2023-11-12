package christmas.view;

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
        for (Entry<String, Integer> entry : orderMenu.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + "개");
        }
    }
}