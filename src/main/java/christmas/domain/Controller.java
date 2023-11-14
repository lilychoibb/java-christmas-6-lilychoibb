package christmas.domain;

import christmas.model.CalculateDiscount;
import christmas.model.ExpectedVisitDate;
import christmas.model.OrderAmount;
import christmas.model.OrderedItem;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Controller {
    public static final int EVENT_YEAR = 2023;
    public static final int EVENT_MONTH = 12;
    private final Service service;
    private final OutputView outputView;
    private final InputView inputView;
    private final CalculateDiscount discount;
    private final OrderAmount orderAmount;

    public Controller(Service service, OutputView outputView, InputView inputView, CalculateDiscount discount,
                      OrderAmount orderAmount) {
        this.service = service;
        this.outputView = outputView;
        this.inputView = inputView;
        this.discount = discount;
        this.orderAmount = orderAmount;
    }

    public void eventPlannerLogic() {
        outputView.startMessage();

        ExpectedVisitDate expectedVisitDate = inputExpectedVisitData();
        List<OrderedItem> orderedItems = inputOrderMenu();

        processOrderSpecification(expectedVisitDate, orderedItems);
        showEventPromotions(expectedVisitDate, orderedItems);
    }

    private void processOrderSpecification(ExpectedVisitDate expectedVisitDate, List<OrderedItem> orderedItems) {
        outputView.showEventBenefitsMessage(expectedVisitDate);

        System.out.println();
        outputView.showOrderMenu(orderedItems);

        System.out.println();
        orderAmount.calculateTotalOrderAmount(orderedItems);

        outputView.showOrderAmountBeforeDiscount(orderAmount);
    }

    private void showEventPromotions(ExpectedVisitDate expectedVisitDate, List<OrderedItem> orderedItems) {
        System.out.println();
        applyDiscountAndShowDetails(expectedVisitDate, orderedItems, orderAmount);

        int totalDiscountAmount = discount.calculateTotalDiscount();
        int totalDiscountAmountWithoutFreeGift = discount.calculateTotalDiscountWithoutFreeGift();

        System.out.println();
        outputView.showBenefitsHistory(discount, totalDiscountAmount);

        System.out.println();
        outputView.showTotalBenefitAmount(totalDiscountAmount);

        processOrderResult(totalDiscountAmount, totalDiscountAmountWithoutFreeGift);
    }

    private void processOrderResult(int totalDiscountAmount, int totalDiscountAmountWithoutFreeGift) {
        System.out.println();
        orderAmount.calculateDiscountedTotalPayment(totalDiscountAmountWithoutFreeGift);
        outputView.showDiscountedTotalPayment(orderAmount);

        System.out.println();
        String badgeName = service.getEventBadgeName(totalDiscountAmount);
        outputView.showEventBadge(badgeName);
    }

    private void applyDiscountAndShowDetails(ExpectedVisitDate expectedVisitDate, List<OrderedItem> orderedItems,
                                             OrderAmount orderAmount) {
        String result = "없음";
        if (orderAmount.isEventApply()) {
            discountLogic(expectedVisitDate, orderedItems);
            result = discount.determineGiveFreeGift(orderAmount);
        }

        outputView.showPromotionalItems(result, getFreeGiftQuantity(result));
    }

    private ExpectedVisitDate inputExpectedVisitData() {
        String inputDate = inputView.promptForExpectedVisitDate();
        String cleanDate = service.removeBlank(inputDate);

        try {
            service.isValidData(cleanDate);
            return new ExpectedVisitDate(Integer.parseInt(cleanDate));
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorMessage.INVALID_DATE.getMessage());
            return inputExpectedVisitData();
        }
    }

    private List<OrderedItem> inputOrderMenu() {
        try {
            String inputOrder = inputView.promptForMenuOrder();
            String cleanOrder = service.removeBlank(inputOrder);

            return service.checkAndExtractOrder(cleanOrder);
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorMessage.INVALID_ORDER.getMessage());
            return inputOrderMenu();
        }
    }

    private void discountLogic(ExpectedVisitDate expectedVisitDate, List<OrderedItem> orderedItems) {
        int dayOfMonth = expectedVisitDate.expectedVisitDate();
        LocalDate date = LocalDate.of(EVENT_YEAR, EVENT_MONTH, dayOfMonth);

        discount.determineDiscountByDate(dayOfMonth);
        discount.determineDiscountByDay(service, date, orderedItems);
        discount.determineDiscountBySpecialDay(service, date, dayOfMonth);
    }

    private int getFreeGiftQuantity(String result) {
        int quantity = 0;

        if (Objects.equals(result, "샴페인")) {
            quantity = 1;
        }

        return quantity;
    }
}