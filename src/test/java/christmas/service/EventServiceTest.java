package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.Event;
import christmas.model.Order;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventServiceTest {

    EventService eventService;
    OrderService orderService;
    Order order;

    @BeforeEach
    void seuUp() {
        eventService = new EventService();
        orderService = new OrderService();
        order = new Order(orderService.convertStringToCollection("티본스테이크-1,바비큐립-1,초코케이크-1,제로콜라-1"));
    }

    @DisplayName("크리스마스 디데이 할인 테스트")
    @Test
    void calculateXmasDiscount() {
        int day1 = 3;
        int day2 = 26;

        assertThat(eventService.calculateXmasDiscount(day1))
                .isEqualTo(-1200);
        assertThat(eventService.calculateXmasDiscount(day2))
                .isEqualTo(0);
    }
    
    @DisplayName("평일/주말 구분 테스트")
    @Test
    void isWeekend() {
        int weekday = 3;
        int weekend = 8;

        assertThat(eventService.isWeekend(weekday)).isFalse();
        assertThat(eventService.isWeekend(weekend)).isTrue();
    }

    @DisplayName("평일/주말 구분하여 할인 테스트")
    @Test
    void calculateWeekdayDiscount() {
        int weekday = 3;
        int weekend = 8;
        HashMap<String, Integer> benefitDetails = new HashMap<>();
        eventService.determineDayDiscount(weekday, benefitDetails, order.map());
        eventService.determineDayDiscount(weekend, benefitDetails, order.map());

        assertThat(benefitDetails).containsEntry("평일 할인: ", -2023);
        assertThat(benefitDetails).containsEntry("주말 할인: ", -4046);
    }

    @DisplayName("이벤트 달력에 별이 있는 날인지 테스트")
    @Test
    void hasStarInCalendar() {
        int weekday = 3;
        int weekend = 8;

        assertThat(eventService.hasStarInCalendar(weekday)).isTrue();
        assertThat(eventService.hasStarInCalendar(weekend)).isFalse();
    }
}