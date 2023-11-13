package christmas.model;

import christmas.domain.Menu;
import java.util.List;

public class OrderAmount {
    private int orderAmount;

    public OrderAmount() {
    }

    public int calculateTotalOrderAmount(List<OrderedItem> orderedItems) {
        int orderAmount = 0;

        for (OrderedItem item:orderedItems) {
            orderAmount += Menu.valueOf(item.getMenu()).getPrice() * item.getQuantity();
        }

        return orderAmount;
    }
}
