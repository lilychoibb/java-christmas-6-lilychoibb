package christmas.model;

import christmas.domain.Menu;
import java.util.Objects;

public class OrderedItem {
    private final String menu;
    private final int quantity;

    public OrderedItem(String menu, int quantity) {
        validator(menu);
        this.menu = menu;
        this.quantity = quantity;
    }

    private void validator(String menu) {
        if (!menuValidator(menu)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean menuValidator(String menu) {
        for (Menu existedMenu : Menu.values()) {
            if (Objects.equals(menu, existedMenu.getName())) {
                return true;
            }
        }
        return false;
    }
}
