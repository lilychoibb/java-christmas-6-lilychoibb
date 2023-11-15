package christmas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderAmountTest {
    private static OrderAmount orderAmount;

    @BeforeAll
    static void constructOrderAmount() {
        orderAmount = new OrderAmount();
    }

    @DisplayName("할인 전 총 주문 금액을 올바르게 계산한다.")
    @Test
    void calculateTotalOrderAmountTest() {
        List<OrderedItem> expectedOrder = Arrays.asList(
                new OrderedItem("티본스테이크", 2),
                new OrderedItem("초코케이크", 1)
        );

        orderAmount.calculateTotalOrderAmount(expectedOrder);
        int actualValue = orderAmount.getOrderAmount();

        assertEquals(125000, actualValue);
    }
}