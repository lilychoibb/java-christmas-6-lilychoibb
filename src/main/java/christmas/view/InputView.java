package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static final int EVENT_MONTH = 12;

    public InputView() {
    }

    public String promptForExpectedVisitDate() {
        System.out.println(EVENT_MONTH + "월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return Console.readLine();
    }

    public String promptForMenuOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return Console.readLine();
    }
}
