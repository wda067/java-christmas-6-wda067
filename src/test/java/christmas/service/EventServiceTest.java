package christmas.service;

import static christmas.constants.Badge.NOTHING;
import static christmas.constants.Badge.SANTA_BADGE;
import static christmas.constants.Badge.STAR_BADGE;
import static christmas.constants.Badge.TREE_BADGE;
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

    @DisplayName("특별 이벤트 할인 테스트")
    @Test
    void calculateSpecialDiscount() {
        int weekday = 3;
        int weekend = 8;

        assertThat(eventService.calculateSpecialDiscount(weekday)).isEqualTo(-1000);
        assertThat(eventService.calculateSpecialDiscount(weekend)).isEqualTo(0);
    }

    @DisplayName("혜택 금엑에 따른 이벤트 배지 부여 테스트")
    @Test
    void determineBadge() {
        int discountAmount1 = 22_000;
        int discountAmount2 = 15_000;
        int discountAmount3 = 6_000;
        int discountAmount4 = 3_000;

        assertThat(eventService.determineBadge(discountAmount1)).isEqualTo(SANTA_BADGE.getBadge());
        assertThat(eventService.determineBadge(discountAmount2)).isEqualTo(TREE_BADGE.getBadge());
        assertThat(eventService.determineBadge(discountAmount3)).isEqualTo(STAR_BADGE.getBadge());
        assertThat(eventService.determineBadge(discountAmount4)).isEqualTo(NOTHING.getBadge());
    }
}