package christmas.controller;

import christmas.model.Order;
import christmas.service.EventService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;

public class ChristmasController {

    private final HashMap<String, Integer> benefitDetails = new HashMap<>();
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    OrderService orderService = new OrderService();
    EventService eventService = new EventService();
    Order order;
    private String visitDate;
    private boolean isProvided;
    private int benefitAmount;

    public void inputVisitDate() {
        outputView.printWelcomeMessage();
        visitDate = inputView.inputVisitDate();
        orderService.validateVisitDate(visitDate);
    }

    public void inputMenuAndCount() {
        String menuAndCount = inputView.inputMenuAndCount();
        order = new Order(orderService.convertStringToCollection(menuAndCount));
        orderService.validateMenuAndCount(menuAndCount);
        outputView.printOverviewMessage(Integer.parseInt(visitDate));
    }
}
