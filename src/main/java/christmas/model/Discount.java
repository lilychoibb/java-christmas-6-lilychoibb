package christmas.model;

import christmas.domain.Menu;
import java.util.List;
import java.util.Objects;

public class Discount {
    public static final int WEEKDAY = 2023;
    public static final int WEEKEND = 2023;
    public static final int SPECIAL = 1000;

    private int christmasDDay = 1000;

    public Discount() {
    }

    private int calculateChristmasDDayDiscount(ExpectedVisitDate expectedVisitDate) {
        if (expectedVisitDate.getExpectedVisitDate() <= 25) {
            for (int i = 1; i < expectedVisitDate.getExpectedVisitDate(); i++) {
                christmasDDay += 100;
            }
        }

        return christmasDDay;
    }

    private int calculateWeekDayDiscount(List<OrderedItem> orderedItems) {
        for (OrderedItem item:orderedItems) {
            Menu menu = Menu.valueOf(item.getMenu());
            if (Objects.equals(menu.getType(), "dessert")) {
                return menu.getPrice() - WEEKDAY;
            }
        }
        return 0;
    }

    private int calculateWeekendDiscount(List<OrderedItem> orderedItems){
        for (OrderedItem item:orderedItems) {
            Menu menu = Menu.valueOf(item.getMenu());
            if (Objects.equals(menu.getType(), "main")) {
                return menu.getPrice() - WEEKEND;
            }
        }
        return 0;
    }
}