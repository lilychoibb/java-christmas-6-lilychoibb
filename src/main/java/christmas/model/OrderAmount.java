package christmas.model;

import christmas.domain.Menu;
import java.util.List;

public class OrderAmount {
    public static final int MINIMUM_ORDER_AMOUNT_FOR_EVENT = 10000;
    private int orderAmount;
    private int discountedTotalPayment;

    public OrderAmount() {
    }

    public void calculateTotalOrderAmount(List<OrderedItem> orderedItems) {
        for (OrderedItem item : orderedItems) {
            orderAmount += Menu.valueOf(item.menu()).getPrice() * item.quantity();
        }
    }

    public void calculateDiscountedTotalPayment(int totalDiscountAmountWithoutFreeGift) {
        discountedTotalPayment = orderAmount + totalDiscountAmountWithoutFreeGift;
    }

    public boolean isEventApply() {
        return orderAmount >= MINIMUM_ORDER_AMOUNT_FOR_EVENT;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public int getDiscountedTotalPayment() {
        return discountedTotalPayment;
    }
}