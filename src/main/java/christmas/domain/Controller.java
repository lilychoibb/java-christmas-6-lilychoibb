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
        inputOrderMenu();

    }

    private void inputExpectedVisitData() {
        String expectedVisitDate = removeBlank(inputView.promptForExpectedVisitDate());

        try {
            isValidData(expectedVisitDate);
            new ExpectedVisitDate(Integer.parseInt(expectedVisitDate));
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorMessage.INVALID_DATE.getMessage());
            inputExpectedVisitData();
        }
    }

    private void isValidData(String inputData){
     if(!isEmptyData(inputData) || !isNumericData(inputData)) {
         throw new IllegalArgumentException();
     }
    }


    private boolean isEmptyData(String inputData) {
        return !inputData.isEmpty();
    }

    private boolean isNumericData(String inputData){
        try {
            for (String numStr : inputData.split("")) {
                Integer.parseInt(numStr);
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void inputOrderMenu() {
        String orderMenu = removeBlank(inputView.promptForMenuOrder());

        try {
            splitMenuAndQuantity(extractMenuItems(orderMenu));
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorMessage.INVALID_ORDER.getMessage());
            inputOrderMenu();
        }
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
}
