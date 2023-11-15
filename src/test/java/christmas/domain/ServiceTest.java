package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.OrderedItem;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ServiceTest {
    private static Service service;

    @BeforeAll
    static void constructService() {
        service = new Service();
    }

    @DisplayName("날짜 입력 값이 공백이면 에러가 발생한다.")
    @Test
    void isEmptyDataTest() {
        assertThatThrownBy(() -> service.isValidData("")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("날짜 입력 값이 숫자가 아니면 에러가 발생한다.")
    @Test
    void isNotNumericDataTest() {
        assertThatThrownBy(() -> service.isValidData("일")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력 값의 공백을 제거한다.")
    @Test
    void removeBlankTest() {
        assertThat(service.removeBlank("티본스테이크-1, 바비큐립-1")).isEqualTo("티본스테이크-1,바비큐립-1");
    }

    @DisplayName("주문 메뉴 리스트를 올바르게 생성한다.")
    @Test
    void checkAndExtractOrderTest() {
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        List<OrderedItem> expectedOrder = Arrays.asList(
                new OrderedItem("티본스테이크", 1),
                new OrderedItem("바비큐립", 1),
                new OrderedItem("초코케이크", 2),
                new OrderedItem("제로콜라", 1)
        );

        List<OrderedItem> actualOrder = service.checkAndExtractOrder(input);

        assertThat(actualOrder).hasSize(expectedOrder.size())
                .containsExactlyElementsOf(expectedOrder);
    }

    @DisplayName("음료만 주문한 경우 예외가 발생한다.")
    @Test
    void hasBeverageOnlyOrderTest() {
        assertThatThrownBy(() -> service.checkAndExtractOrder("제로콜라-1,레드와인-1")).isInstanceOf(
                IllegalArgumentException.class);
    }

    @DisplayName("중복 메뉴를 주문한 경우 예외가 발생한다.")
    @Test
    void hasDuplicateMenuTest() {
        assertThatThrownBy(() -> service.checkAndExtractOrder("티본스테이크-1,티본스테이크-1")).isInstanceOf(
                IllegalArgumentException.class);
    }

    @DisplayName("메뉴를 20개 이상 주문한 경우 예외가 발생한다.")
    @Test
    void checkMenuQuantityTest() {
        assertThatThrownBy(() -> service.checkAndExtractOrder("티본스테이크-19,제로콜라-1,레드와인-1")).isInstanceOf(
                IllegalArgumentException.class);
    }
}