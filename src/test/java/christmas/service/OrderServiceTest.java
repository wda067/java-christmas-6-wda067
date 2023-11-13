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

    @DisplayName("메뉴와 개수가 쉼표로 구분되지 않을 때 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-3,제로콜라-2바비큐립-2", "타파스-2해산물파스타-1"})
    void isMenuAndCountNotDividedByComma(String menuAndCount) {
        map = orderService.convertStringToCollection(menuAndCount);
        Assertions.assertThatThrownBy(() -> orderService.validateMenuAndCount(menuAndCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 메뉴를 입력할 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-1,양송이수프-2", "바비큐립-1,티본스테이크-1,바비큐립-1"})
    void containMenuDuplicateMenu(String menuAndCount) {
        map = orderService.convertStringToCollection(menuAndCount);
        Assertions.assertThatThrownBy(() -> orderService.validateMenuAndCount(menuAndCount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}