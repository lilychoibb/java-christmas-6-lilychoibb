package christmas.model;

import christmas.domain.Menu;
import java.util.Arrays;
import java.util.Objects;

public record OrderedItem(String menu, int quantity) {
    public OrderedItem {
        validator(menu, quantity);
    }

    private void validator(String menu, int quantity) {
        if (!menuValidator(menu) || !quantityValidator(quantity)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean menuValidator(String menu) {
        return Arrays.stream(Menu.values())
                .anyMatch(existedMenu -> Objects.equals(menu, existedMenu.getName()));
    }

    private boolean quantityValidator(int quantity) {
        return 1 <= quantity;
    }
}