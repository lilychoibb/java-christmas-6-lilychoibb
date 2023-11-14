package christmas.view;

import christmas.domain.Discount;
import christmas.model.CalculateDiscount;
import christmas.model.ExpectedVisitDate;
import christmas.model.OrderAmount;
import christmas.model.OrderedItem;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    public static final int EVENT_MONTH = 12;
    public static final String WON = "원";
    private final DecimalFormat decFormat;

    public OutputView() {
        decFormat = new DecimalFormat("###,###");
    }

    public void startMessage() {
        System.out.println("안녕하세요! 우테코 식당 " + EVENT_MONTH + "월 이벤트 플래너입니다.");
    }

    public void showEventBenefitsMessage(ExpectedVisitDate expectedVisitDate) {
        System.out.println(
                EVENT_MONTH + "월 " + expectedVisitDate.expectedVisitDate() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void showOrderMenu(List<OrderedItem> orderedItems) {
        System.out.println("<주문 메뉴>");

        for (OrderedItem item : orderedItems) {
            System.out.println(item.menu() + " " + item.quantity() + "개");
        }
    }

    public void showOrderAmountBeforeDiscount(OrderAmount orderAmount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(decFormat.format(orderAmount.getOrderAmount()) + WON);
    }

    public void showPromotionalItems(String item, int quantity) {
        System.out.println("<증정 메뉴>");

        if (quantity == 0) {
            System.out.println(item);
        }

        if (quantity > 0) {
            System.out.println(item + " " + quantity + "개");
        }
    }

    public void showBenefitsHistory(CalculateDiscount discount, int totalDiscountAmount) {
        System.out.println("<혜택 내역>");

        if (totalDiscountAmount != 0) {
            printBenefit(Discount.CHRISTMAS_D_DAY, discount.getChristmasDDay());
            printBenefit(Discount.WEEKDAY, discount.getTotalWeekDayDiscount());
            printBenefit(Discount.WEEKEND, discount.getTotalWeekendDiscount());
            printBenefit(Discount.SPECIAL_DAY, discount.getSpecialDayDiscount());
            printBenefit(Discount.FREE_GIFT, discount.getFreeGift());
        }

        if (totalDiscountAmount == 0) {
            System.out.println("없음");
        }
    }

    private void printBenefit(Discount discountType, int discountAmount) {
        if (discountAmount != 0) {
            System.out.println(discountType.getBenefit() + ": " + decFormat.format(discountAmount) + WON);
        }
    }

    public void showTotalBenefitAmount(int totalDiscountAmount) {
        System.out.println("<총혜택 금액>");
        System.out.println(decFormat.format(totalDiscountAmount) + WON);
    }

    public void showDiscountedTotalPayment(OrderAmount orderAmount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(decFormat.format(orderAmount.getDiscountedTotalPayment()) + WON);
    }

    public void showEventBadge(String badge) {
        System.out.println("<" + EVENT_MONTH + "월 이벤트 배지>");
        System.out.println(badge);
    }
}