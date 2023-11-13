package christmas.view;

import static christmas.constants.Message.INPUT_MENU_AND_COUNT;
import static christmas.constants.Message.INPUT_VISIT_DATE_MESSAGE;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String inputVisitDate() {
        System.out.println(INPUT_VISIT_DATE_MESSAGE.getMessage());
        return Console.readLine();
    }

    public String inputMenuAndCount() {
        System.out.println(INPUT_MENU_AND_COUNT.getMessage());
        return Console.readLine();
    }
}
