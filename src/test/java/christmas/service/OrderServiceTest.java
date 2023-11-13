package christmas.service;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderServiceTest {

    OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @DisplayName("방문 날짜가 숫자가 아닌 문자가 포함될 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {" 3", "a", "12b"})
    void containVisitDateNonDigit(String visitDate) {
        Assertions.assertThatThrownBy(() -> orderService.validateVisitDate(visitDate))
                .isInstanceOf(IllegalArgumentException.class);
    }
}