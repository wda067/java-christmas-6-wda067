package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderServiceTest {

    OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
        orderService.initializeVariables();
    }

    @DisplayName("방문 날짜가 숫자가 아닌 문자가 포함될 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {" 3", "a", "12b"})
    void containVisitDateNonDigit(String visitDate) {
        assertThatThrownBy(() -> orderService.validateVisitDate(visitDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("방문 날짜가 1에서 31까지 숫자가 아닐 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32"})
    void containVisitDateWrongRange(String visitDate) {
        assertThatThrownBy(() -> orderService.validateVisitDate(visitDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴와 개수가 올바른 입력값이 아닐 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"asfd", "123", " av42"})
    void hasMenuAndCountIncorrectFormat(String menuAndCount) {
        Order order = new Order(orderService.convertStringToCollection(menuAndCount));
        assertThatThrownBy(() -> orderService.validateMenuAndCount(menuAndCount, order.map()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴와 개수가 쉼표로 구분되지 않을 때 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-3,제로콜라-2바비큐립-2", "타파스-2해산물파스타-1"})
    void isMenuAndCountNotDividedByComma(String menuAndCount) {
        Order order = new Order(orderService.convertStringToCollection(menuAndCount));
        assertThatThrownBy(() -> orderService.validateMenuAndCount(menuAndCount, order.map()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 메뉴를 입력할 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-1,양송이수프-1", "바비큐립-1,티본스테이크-1,바비큐립-2"})
    void containMenuDuplicateMenu(String menuAndCount) {
        Order order = new Order(orderService.convertStringToCollection(menuAndCount));
        assertThatThrownBy(() -> orderService.validateMenuAndCount(menuAndCount, order.map()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1미만의 개수를 입력할 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-0,타파스-2", "바비큐립-1,티본스테이크-0,제로콜라-1"})
    void isCountUnderOne(String menuAndCount) {
        Order order = new Order(orderService.convertStringToCollection(menuAndCount));
        assertThatThrownBy(() -> orderService.validateMenuAndCount(menuAndCount, order.map()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("존재하지 않는 메뉴를 입력할 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"양념치킨-1,타파스-2", "바비큐립-1,티본스테이크-2,제로사이다-1"})
    void containMenuNotExisted(String menuAndCount) {
        Order order = new Order(orderService.convertStringToCollection(menuAndCount));
        assertThatThrownBy(() -> orderService.validateMenuAndCount(menuAndCount, order.map()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문할 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-3", "레드와인-2"})
    void isOnlyBeverage(String menuAndCount) {
        Order order = new Order(orderService.convertStringToCollection(menuAndCount));
        assertThatThrownBy(() -> orderService.validateMenuAndCount(menuAndCount, order.map()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴를 20개 초과 주문할 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"크리스마스파스타-5,시저샐러드-16", "바비큐립-23"})
    void isCountOverTwenty(String menuAndCount) {
        Order order = new Order(orderService.convertStringToCollection(menuAndCount));
        assertThatThrownBy(() -> orderService.validateMenuAndCount(menuAndCount, order.map()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴 할인 전 가격 테스트")
    @Test
    void countMenu() {
        String menuAndCount = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        Order order = new Order(orderService.convertStringToCollection(menuAndCount));
        orderService.calculateTotal(order.map());
        Assertions.assertThat(orderService.getTotal())
                .isEqualTo(142000);
    }

    @DisplayName("샴페인 증정 테스트")
    @Test
    void isChampagneProvided() {
        int total1 = 50000;
        int total2 = 130000;
        assertThat(orderService.isChampagneProvided(total1)).isFalse();
        assertThat(orderService.isChampagneProvided(total2)).isTrue();
    }
}