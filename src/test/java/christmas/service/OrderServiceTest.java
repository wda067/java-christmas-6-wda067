package christmas.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderServiceTest {

    OrderService orderService;
    HashMap<String, Integer> map;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
        map = new HashMap<>();
    }

    @DisplayName("방문 날짜가 숫자가 아닌 문자가 포함될 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {" 3", "a", "12b"})
    void containVisitDateNonDigit(String visitDate) {
        Assertions.assertThatThrownBy(() -> orderService.validateVisitDate(visitDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("방문 날짜가 1에서 31까지 숫자가 아닐 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32"})
    void containVisitDateWrongRange(String visitDate) {
        Assertions.assertThatThrownBy(() -> orderService.validateVisitDate(visitDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴와 개수가 올바른 입력값이 아닐 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"asfd", "123", " av42"})
    void hasMenuAndCountIncorrectFormat(String menuAndCount) {
        map = orderService.convertStringToCollection(menuAndCount);
        Assertions.assertThatThrownBy(() -> orderService.validateMenuAndCount(menuAndCount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}