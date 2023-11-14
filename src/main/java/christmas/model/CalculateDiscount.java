package christmas.model;

import static christmas.domain.Discount.CHRISTMAS_D_DAY;
import static christmas.domain.Discount.WEEKDAY;
import static christmas.domain.Discount.WEEKEND;

import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CalculateDiscount {
    public static final int CHRISTMAS_DISCOUNT_DEADLINE = 25;
    public static final int DAILY_INCREASING_DISCOUNT_AMOUNT = 100;
    public static final int MIN_ORDER_AMOUNT_FOR_GIFT = 120000;
    private int christmasDDay = 0;
    private int totalWeekDayDiscount = 0;
    private int totalWeekendDiscount = 0;
    private int specialDayDiscount = 0;
    private int freeGift = 0;

    public CalculateDiscount() {
    }

    public void determineDiscountByDate(int dayOfMonth) {
        if (dayOfMonth <= CHRISTMAS_DISCOUNT_DEADLINE) {
            calculateChristmasDDayDiscount(dayOfMonth);
        }
    }

    public void determineDiscountByDay(Service service, LocalDate date, List<OrderedItem> orderedItems) {
        if (service.isWeekDay(date)) {
            calculateWeekDayDiscount(orderedItems);
            return;
        }

        calculateWeekendDiscount(orderedItems);
    }

    public void determineDiscountBySpecialDay(Service service, LocalDate date, int dayOfMonth) {
        if (service.isSpecialDay(date, dayOfMonth)) {
            calculateSpecialDayDiscount();
        }
    }

    public String determineGiveFreeGift(OrderAmount orderAmount) {
        if (orderAmount.getOrderAmount() >= MIN_ORDER_AMOUNT_FOR_GIFT) {
            return calculateFreeGift();
        }

        return "없음";
    }

    private void calculateChristmasDDayDiscount(int dayOfMonth) {
        christmasDDay -= CHRISTMAS_D_DAY.getPrice();

        for (int i = 1; i < dayOfMonth; i++) {
            christmasDDay -= DAILY_INCREASING_DISCOUNT_AMOUNT;
        }
    }

    private void calculateWeekDayDiscount(List<OrderedItem> orderedItems) {
        for (OrderedItem item : orderedItems) {
            Menu menu = Menu.valueOf(item.menu());
            if (Objects.equals(menu.getType(), "dessert")) {
                totalWeekDayDiscount -= WEEKDAY.getPrice() * item.quantity();
                return;
            }
        }
    }

    private void calculateWeekendDiscount(List<OrderedItem> orderedItems) {
        for (OrderedItem item : orderedItems) {
            Menu menu = Menu.valueOf(item.menu());
            if (Objects.equals(menu.getType(), "main")) {
                totalWeekendDiscount -= WEEKEND.getPrice();
                return;
            }
        }
    }

    private void calculateSpecialDayDiscount() {
        specialDayDiscount -= Discount.SPECIAL_DAY.getPrice();
    }

    private String calculateFreeGift() {
        freeGift -= Discount.FREE_GIFT.getPrice();
        return Menu.샴페인.getName();
    }

    public int calculateTotalDiscountWithoutFreeGift() {
        return christmasDDay + totalWeekDayDiscount + totalWeekendDiscount + specialDayDiscount;
    }

    public int calculateTotalDiscount() {
        return christmasDDay + totalWeekDayDiscount + totalWeekendDiscount + specialDayDiscount + freeGift;
    }

    public int getChristmasDDay() {
        return christmasDDay;
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