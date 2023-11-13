package christmas.controller;

import static christmas.model.Menu.CHAMPAGNE;

import christmas.model.Order;
import christmas.service.EventService;
import christmas.service.OrderService;
import christmas.util.NumberFormatter;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;
import java.util.Map;

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
    
    public ChristmasController() {
        inputVisitDate();
        inputMenuAndCount();
        printOrderMenu();
        printTotalBeforeDiscount();
        isChampagneProvided();
        printGiftMenu();
        setBenefitDetails();
        printBenefitDetails();
        printTotalBenefit();
        printTotalAmount();
        printEventBadge();
    }

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

    public void setBenefitDetails() {
        if (orderService.getTotal() >= 10_000) {
            setXmasDiscount();
            setDayDiscount();
            setSpecialDiscount();
            setGiftEventBenefit();
        }
    }

    public void setXmasDiscount() {
        int discount = eventService.calculateXmasDiscount(Integer.parseInt(visitDate));
        if (discount != 0) {
            benefitDetails.put("크리스마스 디데이 할인: ", discount);
        }
    }

    public void setDayDiscount() {
        boolean isWeekend = eventService.isWeekend(Integer.parseInt(visitDate));
        int weekendDiscount = eventService.calculateWeekendDiscount(order.map());
        int weekdayDiscount = eventService.calculateWeekdayDiscount(order.map());
        if (isWeekend && weekendDiscount != 0) {
            benefitDetails.put("주말 할인: ", weekendDiscount);
        }
        if (!isWeekend && weekdayDiscount != 0) {
            benefitDetails.put("평일 할인: ", weekdayDiscount);
        }
    }

    public void setSpecialDiscount() {
        int discount = eventService.calculateSpecialDiscount(Integer.parseInt(visitDate));
        if (discount != 0) {
            benefitDetails.put("특별 할인: ", discount);
        }
    }

    public void setGiftEventBenefit() {
        int discount = -1 * CHAMPAGNE.getPrice();
        if (isProvided) {
            benefitDetails.put("증정 이벤트: ", discount);
        }
    }

    public void printBenefitDetails() {
        outputView.printBenefitDetails(benefitDetails);
    }

    public void printTotalBenefit() {
        benefitAmount = benefitDetails.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        outputView.printTotalBenefitAmount(benefitAmount);
    }

    public void printTotalAmount() {
        int discount = benefitDetails.entrySet().stream()
                .filter(event -> !event.getKey().equals("증정 이벤트: "))
                .mapToInt(Map.Entry::getValue)
                .sum();
        outputView.printExpectedTotalAfterDiscount(orderService.getTotal(), discount);
    }

    public void printEventBadge() {
        String badge = eventService.determineBadge(Math.abs(benefitAmount));
        outputView.printEventBadge(badge);
    }
}
