package christmas.view;

public class OutputView {
    public static final int EVENT_MONTH = 12;

    public OutputView() {
    }

    public void startMessage() {
        System.out.println("안녕하세요! 우테코 식당" + EVENT_MONTH + "월 이벤트 플래너입니다.");
    }

    public void showEventBenefitsMessage() {
        System.out.println(EVENT_MONTH + "월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"); //날짜는 변경할 수 있게끔 수정 필요
    }
}