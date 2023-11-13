package christmas.model;

public class Discount {
    public static final int WEEKDAY = 2023;
    public static final int WEEKEND = 2023;
    public static final int SPECIAL = 1000;

    private int christmasDDay = 1000;

    public Discount() {
    }

    private int calculateChristmasDDayDiscount(ExpectedVisitDate expectedVisitDate) {
        if(expectedVisitDate.getExpectedVisitDate() <= 25){
            for (int i = 1; i < expectedVisitDate.getExpectedVisitDate(); i++) {
                christmasDDay += 100;
            }
        }

        return christmasDDay;
    }
}