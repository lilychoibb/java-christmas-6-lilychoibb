package christmas.domain;

public enum Discount {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인", 1000),
    WEEKDAY("평일 할인", 2023),
    WEEKEND("주말 할인", 2023),
    SPECIAL_DAY("특별 할인", 1000),
    FREE_GIFT("증정 이벤트", 25000);

    private final String benefit;
    private final int price;

    Discount(String benefit, int price) {
        this.benefit = benefit;
        this.price = price;
    }

    public String getBenefit() {
        return benefit;
    }

    public int getPrice() {
        return price;
    }
}