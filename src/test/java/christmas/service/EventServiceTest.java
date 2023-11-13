package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventServiceTest {

    EventService eventService;

    @BeforeEach
    void seuUp() {
        eventService = new EventService();
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

}