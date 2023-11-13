package christmas.view;

import christmas.model.Discount;
import christmas.model.ExpectedVisitDate;
import christmas.model.OrderedItem;
import java.text.DecimalFormat;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class OutputView {
    public static final int EVENT_MONTH = 12;

    public OutputView() {
    }

    public void startMessage() {
        System.out.println("안녕하세요! 우테코 식당 " + EVENT_MONTH + "월 이벤트 플래너입니다.");
    }

    public void showEventBenefitsMessage(ExpectedVisitDate expectedVisitDate) {
        System.out.println(
                EVENT_MONTH + "월 " + expectedVisitDate.getExpectedVisitDate() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void showOrderMenu(List<OrderedItem> orderedItems) {
        System.out.println("<주문 메뉴>");

        for (OrderedItem item : orderedItems) {
            System.out.println(item.getMenu() + " " + item.getQuantity() + "개");
        }
    }

    public void showOrderAmountBeforeDiscount(int orderAmount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");

        System.out.println("<할인 전 총주문 금액>");
        System.out.println(decFormat.format(orderAmount) + "원");
    }

    public void showPromotionalItems(String item, int quantity) {
        System.out.println("<증정 메뉴>");
        System.out.print(item + " ");

        if (quantity > 0) {
            System.out.println(quantity + "개");
        }
    }

    public void showBenefitsHistory(Discount discount, ExpectedVisitDate expectedVisitDate) {
        DecimalFormat decFormat = new DecimalFormat("###,###");

        System.out.println("<혜택 내역>");
        System.out.println(
                "크리스마스 디데이 할인: -" + decFormat.format(discount.calculateChristmasDDayDiscount(expectedVisitDate)) + "원");

        if (discount.getTotalWeekDayDiscount() != 0) {
            System.out.println(christmas.domain.Discount.WEEKDAY.getBenefit() + ": -" + decFormat.format(
                    discount.getTotalWeekDayDiscount()) + "원");
        }

        if (discount.getTotalWeekendDiscount() != 0) {
            System.out.println(christmas.domain.Discount.WEEKEND.getBenefit() + ": -" + decFormat.format(
                    discount.getTotalWeekendDiscount()) + "원");
        }

        System.out.println(christmas.domain.Discount.SPECIAL_DAY.getBenefit() + ": -" + decFormat.format(
                discount.getSpecialDayDiscount()) + "원");
        System.out.println(
                christmas.domain.Discount.FREE_GIFT.getBenefit() + ": -" + decFormat.format(discount.getFreeGift())
                        + "원");
    }

    public void showTotalBenefitAmount(int totalDiscountAmount) {
        DecimalFormat decFormat = new DecimalFormat("###,###");
        System.out.println("<총혜택 금액>");
        System.out.println("-" + decFormat.format(totalDiscountAmount) + "원");
    }

    public void showDiscountedTotalPayment(int orderAmount, int totalDiscountAmountWithoutFreeGift) {
        DecimalFormat decFormat = new DecimalFormat("###,###");
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(decFormat.format(orderAmount - totalDiscountAmountWithoutFreeGift) + "원");
    }
    }
}