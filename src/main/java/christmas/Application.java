package christmas;

import christmas.domain.Controller;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(new OutputView(), new InputView());
        controller.eventPlannerLogic();
    }
}