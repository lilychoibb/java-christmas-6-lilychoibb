package christmas.domain;

import christmas.model.ExpectedVisitDate;
import christmas.model.OrderedItem;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final OutputView outputView;
    private final InputView inputView;

    public Controller(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void eventPlannerLogic() {
        outputView.startMessage();
        inputExpectedVisitData();

        String orderMenu = inputView.promptForMenuOrder();
        splitMenuAndQuantity(extractMenuItems(orderMenu));
    }

    private void inputExpectedVisitData() {
        int expectedVisitDate = inputView.promptForExpectedVisitDate();

        try {
            new ExpectedVisitDate(expectedVisitDate);
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorMessage.INVALID_DATE.getMessage());
            inputExpectedVisitData();
        }
    }

    private List<String> extractMenuItems(String orderMenu) {
        return List.of(orderMenu.split(","));
    }

    private List<OrderedItem> splitMenuAndQuantity(List<String> orderMenu) {
        List<OrderedItem> menuQuantity = new ArrayList<>();

        for (String item : orderMenu) {
            String[] menuAndQuantity = item.split("-");
            String menu = menuAndQuantity[0];
            int quantity = Integer.parseInt(menuAndQuantity[1]);
            menuQuantity.add(new OrderedItem(menu, quantity));
        }

        return menuQuantity;
    }
}
