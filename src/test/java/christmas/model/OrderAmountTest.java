package christmas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @DisplayName("할인 후 예상 결제 금액을 올바르게 계산한다.")
    @Test
    void calculateDiscountedTotalPaymentTest() {
        orderAmount.calculateDiscountedTotalPayment(-5000);
        int actualValue = orderAmount.getDiscountedTotalPayment();

        assertEquals(120000, actualValue);
    }

    @DisplayName("총 주문 금액이 10000원 이상일때 이벤트가 적용된다.")
    @Test
    void isEventApplyTest() {
        assertTrue(orderAmount.isEventApply());
    }
}