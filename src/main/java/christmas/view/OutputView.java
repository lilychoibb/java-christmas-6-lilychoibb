package christmas.view;

import christmas.model.CalculateDiscount;
import christmas.model.ExpectedVisitDate;
import christmas.model.OrderAmount;
import christmas.model.OrderedItem;
import java.util.List;

public interface OutputView {
    void startMessage();
    void showEventBenefitsMessage(ExpectedVisitDate expectedVisitDate);
    void showOrderMenu(List<OrderedItem> orderedItems);
    void showOrderAmountBeforeDiscount(OrderAmount orderAmount);
    void showPromotionalItems(String item, int quantity);
    void showBenefitsHistory(CalculateDiscount discount, int totalDiscountAmount);
    void showTotalBenefitAmount(int totalDiscountAmount);
    void showDiscountedTotalPayment(OrderAmount orderAmount);
    void showEventBadge(String badge);
}