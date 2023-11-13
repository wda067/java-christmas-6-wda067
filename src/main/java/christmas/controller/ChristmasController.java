package christmas.controller;

import static christmas.constants.Event.CHRISTMAS_EVENT;
import static christmas.constants.Event.GIFT_EVENT;
import static christmas.constants.Event.SPECIAL_EVENT;
import static christmas.constants.Event.WEEKDAY_EVENT;
import static christmas.constants.Event.WEEKEND_EVENT;
import static christmas.constants.NumberEnum.MINIMUM_EVENT_AMOUNT;
import static christmas.constants.NumberEnum.NEGATIVE_MULTIPLIER;
import static christmas.constants.NumberEnum.ZERO_VALUE;
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
        inputView.printWelcomeMessage();
        inputView.printInputVisitDateMessage();
        while (true) {
            try {
                visitDate = inputView.inputVisitDate();
                orderService.validateVisitDate(visitDate);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void inputMenuAndCount() {
        inputView.printInputMenuAndCountMessage();
        while (true) {
            try {
                String menuAndCount = inputView.inputMenuAndCount();
                order = new Order(orderService.convertStringToCollection(menuAndCount));
                orderService.validateMenuAndCount(menuAndCount);
                outputView.printOverviewMessage(Integer.parseInt(visitDate));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
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
        if (orderService.getTotal() >= MINIMUM_EVENT_AMOUNT.getValue()) {
            setXmasDiscount();
            setDayDiscount();
            setSpecialDiscount();
            setGiftEventBenefit();
        }
    }

    public void setXmasDiscount() {
        int discount = eventService.calculateXmasDiscount(Integer.parseInt(visitDate));
        if (discount != 0) {
            benefitDetails.put(CHRISTMAS_EVENT.getEvent(), discount);
        }
    }

    public void setDayDiscount() {
        boolean isWeekend = eventService.isWeekend(Integer.parseInt(visitDate));
        int weekendDiscount = eventService.calculateWeekendDiscount(order.map());
        int weekdayDiscount = eventService.calculateWeekdayDiscount(order.map());
        if (isWeekend && weekendDiscount != ZERO_VALUE.getValue()) {
            benefitDetails.put(WEEKEND_EVENT.getEvent(), weekendDiscount);
        }
        if (!isWeekend && weekdayDiscount != ZERO_VALUE.getValue()) {
            benefitDetails.put(WEEKDAY_EVENT.getEvent(), weekdayDiscount);
        }
    }

    public void setSpecialDiscount() {
        int discount = eventService.calculateSpecialDiscount(Integer.parseInt(visitDate));
        if (discount != ZERO_VALUE.getValue()) {
            benefitDetails.put(SPECIAL_EVENT.getEvent(), discount);
        }
    }

    public void setGiftEventBenefit() {
        int discount = NEGATIVE_MULTIPLIER.getValue() * CHAMPAGNE.getPrice();
        if (isProvided) {
            benefitDetails.put(GIFT_EVENT.getEvent(), discount);
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
                .filter(event -> !event.getKey().equals(GIFT_EVENT.getEvent()))
                .mapToInt(Map.Entry::getValue)
                .sum();
        outputView.printExpectedTotalAfterDiscount(orderService.getTotal(), discount);
    }

    public void printEventBadge() {
        String badge = eventService.determineBadge(Math.abs(benefitAmount));
        outputView.printEventBadge(badge);
    }
}
