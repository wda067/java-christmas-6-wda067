package christmas.view;

import static christmas.constants.Message.INPUT_EVENT_NOTICE_MESSAGE;
import static christmas.constants.Message.INPUT_MENU_AND_COUNT;
import static christmas.constants.Message.INPUT_MENU_TABLE_MESSAGE;
import static christmas.constants.Message.INPUT_VISIT_DATE_MESSAGE;
import static christmas.constants.Message.OUTPUT_WELCOME_MESSAGE;
import static christmas.util.NumberFormatter.formatNumber;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.Menu;
import java.util.Arrays;
import java.util.stream.Collectors;

public class InputView {

    public void printWelcomeMessage() {
        System.out.println(OUTPUT_WELCOME_MESSAGE.getMessage());
    }

    public void printInputVisitDateMessage() {
        System.out.println(INPUT_VISIT_DATE_MESSAGE.getMessage());
    }

    public void printMenuTable() {
        System.out.println(INPUT_MENU_TABLE_MESSAGE.getMessage());
        Arrays.stream(Menu.values())
                .map(Menu::getMenu)
                .distinct()
                .forEach(menuType -> {
                    System.out.println("<" + menuType + ">");
                    String menuItems = Arrays.stream(Menu.values())
                            .filter(menu -> menu.getMenu().equals(menuType))
                            .map(menu -> menu.getName() + "(" + formatNumber(menu.getPrice()) + ")")
                            .collect(Collectors.joining(", "));
                    System.out.println(menuItems);
                });
    }

    public void printEventNotice() {
        System.out.print((System.lineSeparator()));
        System.out.println(INPUT_EVENT_NOTICE_MESSAGE.getMessage());
    }

    public void printInputMenuAndCountMessage() {
        System.out.println(INPUT_MENU_AND_COUNT.getMessage());
    }

    public String inputVisitDate() {
        return Console.readLine();
    }

    public String inputMenuAndCount() {
        return Console.readLine();
    }
}
