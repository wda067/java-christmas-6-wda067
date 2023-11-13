package christmas.view;

import static christmas.constants.Message.INPUT_MENU_AND_COUNT;
import static christmas.constants.Message.INPUT_VISIT_DATE_MESSAGE;
import static christmas.constants.Message.OUTPUT_WELCOME_MESSAGE;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public void printWelcomeMessage() {
        System.out.println(OUTPUT_WELCOME_MESSAGE.getMessage());
    }

    public void printInputVisitDateMessage() {
        System.out.println(INPUT_VISIT_DATE_MESSAGE.getMessage());
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
