package christmas.view;

import christmas.model.ExpectedVisitDate;
import christmas.model.OrderedItem;
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

        for (OrderedItem item:orderedItems) {
            System.out.println(item.getMenu() + " " + item.getQuantity() + "개");
        }
    }
}