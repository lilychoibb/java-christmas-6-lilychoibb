package christmas.domain;

import christmas.model.OrderedItem;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Service {
    public static final int CHRISTMAS_DAY = 25;

    public Service() {
    }

    public void isValidData(String inputData) {
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

    public String removeBlank(String inputString) {
        return inputString.replaceAll(" ", "");
    }

    public List<OrderedItem> checkAndExtractOrder(String cleanOrder) {
        List<OrderedItem> order = splitMenuAndQuantity(extractMenuItems(cleanOrder));

        hasBeverageOnlyOrder(order);
        hasDuplicateMenu(order);
        checkMenuQuantity(order);

        return order;
    }

    private List<String> extractMenuItems(String orderMenu) {
        return List.of(orderMenu.split(","));
    }

    private List<OrderedItem> splitMenuAndQuantity(List<String> orderMenu) {
        List<OrderedItem> menuQuantity = new ArrayList<>();

        for (String item : orderMenu) {
            OrderedItem orderedItem = createOrderedItem(item);
            menuQuantity.add(orderedItem);
        }

        return menuQuantity;
    }

    private OrderedItem createOrderedItem(String item) {
        String[] menuAndQuantity = item.split("-");

        if (menuAndQuantity.length != 2) {
            throw new IllegalArgumentException();
        }

        String menu = menuAndQuantity[0];
        int quantity = parseQuantity(menuAndQuantity[1]);

        return new OrderedItem(menu, quantity);
    }

    private int parseQuantity(String quantityString) {
        try {
            return Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private void hasBeverageOnlyOrder(List<OrderedItem> items) {
        if (items.stream().allMatch(this::isBeverage)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isBeverage(OrderedItem item) {
        Menu menu = Menu.valueOf(item.menu());
        return Objects.equals(menu.getType(), "beverage");
    }

    private void hasDuplicateMenu(List<OrderedItem> items) {
        Set<String> uniqueMenus = new HashSet<>();

        for (OrderedItem item : items) {
            if (!uniqueMenus.add(item.menu())) {
                throw new IllegalArgumentException();
            }
        }
    }

    public boolean isWeekDay(LocalDate date) {
        return date.getDayOfWeek() != DayOfWeek.FRIDAY && date.getDayOfWeek() != DayOfWeek.SATURDAY;
    }

    public boolean isSpecialDay(LocalDate date, int dayOfMonth) {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY || dayOfMonth == CHRISTMAS_DAY;
    }

    private void checkMenuQuantity(List<OrderedItem> orderedItems) {
        int totalQuantity = orderedItems.stream()
                .mapToInt(OrderedItem::quantity)
                .sum();

        if (totalQuantity > 20) {
            throw new IllegalArgumentException();
        }
    }

    public String getEventBadgeName(int totalDiscountAmount) {
        String badgeName = "없음";

        for (Badge badge : Badge.values()) {
            if (-totalDiscountAmount >= badge.getTotalDiscountAmount()) {
                badgeName = badge.getName();
            }
        }

        return badgeName;
    }
}