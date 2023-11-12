package christmas.model;

public class ExpectedVisitDate {
    private final int expectedVisitDate;

    public ExpectedVisitDate(int expectedVisitDate) {
        validator();
        this.expectedVisitDate = expectedVisitDate;
    }

    private void validator() {
        if (!validateNumberRange(expectedVisitDate)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean validateNumberRange(int expectedVisitDate) {
        return 1 <= expectedVisitDate && expectedVisitDate <= 31;
    }
}
