package christmas.model;

import static christmas.domain.Discount.WEEKDAY;
import static christmas.domain.Discount.WEEKEND;

import christmas.domain.Menu;
import java.util.List;
import java.util.Objects;

public class Discount {
    private int christmasDDay = 1000;
    private int totalWeekDayDiscount = 0;
    private int totalWeekendDiscount = 0;
    private int specialDayDiscount = 0;
    private int freeGift = 0;
    private int totalDiscountAmountWithoutFreeGift = 0;
    private int totalDiscountAmount = 0;

    public Discount() {
    }

    public int calculateChristmasDDayDiscount(ExpectedVisitDate expectedVisitDate) {
        if (expectedVisitDate.getExpectedVisitDate() <= 25) {
            for (int i = 1; i < expectedVisitDate.getExpectedVisitDate(); i++) {
                christmasDDay += 100;
            }
        }

        return christmasDDay;
    }

    public void calculateWeekDayDiscount(List<OrderedItem> orderedItems) {
        for (OrderedItem item : orderedItems) {
            Menu menu = Menu.valueOf(item.getMenu());
            if (Objects.equals(menu.getType(), "dessert")) {
                totalWeekDayDiscount += WEEKDAY.getPrice() * item.getQuantity();
            }
        }
    }

    public void calculateWeekendDiscount(List<OrderedItem> orderedItems) {
        for (OrderedItem item : orderedItems) {
            Menu menu = Menu.valueOf(item.getMenu());
            if (Objects.equals(menu.getType(), "main")) {
                totalWeekendDiscount += WEEKEND.getPrice();
            }
        }
    }

    public void calculateSpecialDayDiscount() {
        specialDayDiscount += christmas.domain.Discount.SPECIAL_DAY.getPrice();
    }

    public void calculateFreeGift() {
        freeGift += christmas.domain.Discount.FREE_GIFT.getPrice();
    }

    public int calculateTotalDiscountWithoutFreeGift() {
        return totalDiscountAmountWithoutFreeGift = christmasDDay + totalWeekDayDiscount + totalWeekendDiscount + specialDayDiscount;
    }

    public int calculateTotalDiscount() {
        return totalDiscountAmount = christmasDDay + totalWeekDayDiscount + totalWeekendDiscount + specialDayDiscount + freeGift;
    }

    public int getTotalWeekDayDiscount() {
        return totalWeekDayDiscount;
    }

    public int getTotalWeekendDiscount() {
        return totalWeekendDiscount;
    }

    public int getSpecialDayDiscount() {
        return specialDayDiscount;
    }

    public int getFreeGift() {
        return freeGift;
    }
}