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
        int expectedVisitDate = inputView.promptForExpectedVisitDate();
        ExpectedVisitDate date = new ExpectedVisitDate(expectedVisitDate);
        String orderMenu = inputView.promptForMenuOrder();
        splitMenuAndQuantity(extractMenuItems(orderMenu));
    }

    private List<String> extractMenuItems(String orderMenu) {
        return List.of(orderMenu.split(","));
    }

    private List<OrderedItem> splitMenuAndQuantity(List<String> orderMenu) {
        List<OrderedItem> menuQuantity = new ArrayList<>();

        for (String item:orderMenu) {
            String[] menuAndQuantity= item.split("-");
            String menu = menuAndQuantity[0];
            int quantity = Integer.parseInt(menuAndQuantity[1]);
            menuQuantity.add(new OrderedItem(menu, quantity));
        }

        return menuQuantity;
    }
}
