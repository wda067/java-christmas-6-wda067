package christmas.controller;

import christmas.model.Order;
import christmas.service.EventService;
import christmas.service.OrderService;
import christmas.util.NumberFormatter;
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

    public void printOrderMenu() {
        outputView.printOrderMenu(order.map());
    }

    public void printTotalBeforeDiscount() {
        orderService.calculateTotal(order.map());
        int total = orderService.getTotal();
        outputView.printTotalOrderAmountBeforeDiscount(NumberFormatter.formatNumber(total));
    }

    public void isChampagneProvided() {
        isProvided = orderService.isChampagneProvided(orderService.getTotal());
    }

    public void printGiftMenu() {
        outputView.printGiftMenu(isProvided);
    }

    public void setXmasDiscount() {
        int discount = eventService.calculateXmasDiscount(Integer.parseInt(visitDate));
        if (discount != 0) {
            benefitDetails.put("크리스마스 디데이 할인: ", discount);
        }
    }
}
