package christmas.model;

import christmas.domain.Menu;
import java.util.Objects;

public class OrderedItem {
    private final String menu;
    private final int quantity;

    public OrderedItem(String menu, int quantity) {
        validator(menu, quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    private void validator(String menu, int quantity) {
        if (!menuValidator(menu) || !quantityValidator(quantity)) {
            throw new IllegalArgumentException();
        }
    }

    // 메뉴판에 있는 메뉴인지 검사
    private boolean menuValidator(String menu) {
        for (Menu existedMenu : Menu.values()) {
            if (Objects.equals(menu, existedMenu.getName())) {
                return true;
            }
        }
        return false;
    }

    //개수 검사
    private boolean quantityValidator(int quantity) {
        return 1 <= quantity && quantity <= 20;
    }
}
