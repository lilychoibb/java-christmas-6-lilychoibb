package christmas;

import christmas.domain.Controller;
import christmas.domain.Service;
import christmas.model.CalculateDiscount;
import christmas.model.OrderAmount;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(new Service(), new OutputView(), new InputView(),
                new CalculateDiscount(), new OrderAmount());
        controller.eventPlannerLogic();
    }
}