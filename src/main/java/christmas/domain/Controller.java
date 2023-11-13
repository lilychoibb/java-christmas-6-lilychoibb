package christmas.domain;

import christmas.model.Discount;
import christmas.model.ExpectedVisitDate;
import christmas.model.OrderAmount;
import christmas.model.OrderedItem;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Controller {
    private final OutputView outputView;
    private final InputView inputView;

    public Controller(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void eventPlannerLogic() {
        outputView.startMessage();

        ExpectedVisitDate expectedVisitDate = inputExpectedVisitData();
        List<OrderedItem> orderedItems = inputOrderMenu();

        outputView.showEventBenefitsMessage(expectedVisitDate);

        System.out.println();
        outputView.showOrderMenu(orderedItems);

        System.out.println();
        OrderAmount orderAmount = new OrderAmount();
        int totalOrderAmount = orderAmount.calculateTotalOrderAmount(orderedItems);
        outputView.showOrderAmountBeforeDiscount(totalOrderAmount);

        int dayOfMonth = expectedVisitDate.getExpectedVisitDate();
        LocalDate date = LocalDate.of(2023, 12, dayOfMonth);

        Discount discount = new Discount();

        if (isWeekDay(date)) {
            discount.calculateWeekDayDiscount(orderedItems);
        }

        if (!isWeekDay(date)) {
            discount.calculateWeekendDiscount(orderedItems);
        }

        if (isSpecialDay(date, dayOfMonth)) {
            discount.calculateSpecialDayDiscount();
        }

        System.out.println();
        if (totalOrderAmount >= 120000) {
            discount.calculateFreeGift();
            outputView.showPromotionalItems(Menu.샴페인.getName(), 1);
        }

        if (totalOrderAmount < 120000) {
            outputView.showPromotionalItems("없음", 0);
        }

        System.out.println();
        outputView.showBenefitsHistory(discount, expectedVisitDate);

        System.out.println();
        int totalDiscountAmount = discount.calculateTotalDiscount();
        outputView.showTotalBenefitAmount(totalDiscountAmount);
    }


    private ExpectedVisitDate inputExpectedVisitData() {
        String inputDate = removeBlank(inputView.promptForExpectedVisitDate());

        try {
            isValidData(inputDate);
            return new ExpectedVisitDate(Integer.parseInt(inputDate));
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorMessage.INVALID_DATE.getMessage());
            inputExpectedVisitData();
        }

        return null;
    }

    private void isValidData(String inputData) {
        if (!isEmptyData(inputData) || !isNumericData(inputData)) {
            throw new IllegalArgumentException();
        }
    }


    private boolean isEmptyData(String inputData) {
        return !inputData.isEmpty();
    }

    private boolean isNumericData(String inputData) {
        try {
            for (String numStr : inputData.split("")) {
                Integer.parseInt(numStr);
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private List<OrderedItem> inputOrderMenu() {
        String orderMenu = removeBlank(inputView.promptForMenuOrder());

        try {
            List<OrderedItem> order = splitMenuAndQuantity(extractMenuItems(orderMenu));
            hasBeverageOnlyOrder(order);
            hasDuplicateMenu(order);
            return order;
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorMessage.INVALID_ORDER.getMessage());
            inputOrderMenu();
        }

        return null;
    }

    private String removeBlank(String inputString) {
        return inputString.replaceAll(" ", "");
    }

    private List<String> extractMenuItems(String orderMenu) {
        return List.of(orderMenu.split(","));
    }


    private List<OrderedItem> splitMenuAndQuantity(List<String> orderMenu) {
        List<OrderedItem> menuQuantity = new ArrayList<>();

        for (String item : orderMenu) {
            String[] menuAndQuantity = item.split("-");
            if (!item.contains("-")) {
                throw new IllegalArgumentException();
            }
            String menu = menuAndQuantity[0];
            int quantity = Integer.parseInt(menuAndQuantity[1]);
            menuQuantity.add(new OrderedItem(menu, quantity));
        }

        return menuQuantity;
    }

    private void hasBeverageOnlyOrder(List<OrderedItem> items) {
        boolean containsNonBeverage = false;

        for (OrderedItem item : items) {
            Menu menu = Menu.valueOf(item.getMenu());

            if (!Objects.equals(menu.getType(), "beverage")) {
                containsNonBeverage = true;
                break;
            }
        }

        if (!containsNonBeverage) {
            // 전부 음료만 주문된 경우에 대한 예외 처리
            throw new IllegalArgumentException();
        }
    }

    private void hasDuplicateMenu(List<OrderedItem> items) {
        List<String> orderedItems1 = new ArrayList<>();
        Set<String> orderedItems2 = new HashSet<>();

        for (OrderedItem item : items) {
            orderedItems1.add(item.getMenu());
        }

        for (OrderedItem item : items) {
            orderedItems2.add(item.getMenu());
        }

        if (orderedItems1.size() != orderedItems2.size()) {
            throw new IllegalArgumentException();
        }

    }

    private boolean isWeekDay(LocalDate date) {
        return date.getDayOfWeek() != DayOfWeek.FRIDAY && date.getDayOfWeek() != DayOfWeek.SATURDAY;
    }

    private boolean isSpecialDay(LocalDate date, int dayOfMonth) {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY || dayOfMonth == 25;
    }
}