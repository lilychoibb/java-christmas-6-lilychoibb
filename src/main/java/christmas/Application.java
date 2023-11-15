package christmas;

import christmas.domain.Controller;
import christmas.domain.Service;
import christmas.model.CalculateDiscount;
import christmas.model.OrderAmount;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.OutputViewImpl;
import christmas.view.OutputViewProxy;

public class Application {
    public static void main(String[] args) {
        OutputView original = new OutputViewImpl();
        OutputView outputView = OutputViewProxy.createProxy(original);

        Controller controller = new Controller(new Service(), outputView, new InputView(),
                new CalculateDiscount(), new OrderAmount(), new OutputViewImpl());

        controller.eventPlannerLogic();
    }
}