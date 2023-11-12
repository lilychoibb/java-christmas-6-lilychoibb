package christmas.model;

public class ExpectedVisitDate {
    public static final int MIN_EXPECTED_VISIT_DATE = 1;
    public static final int MAX_EXPECTED_VISIT_DATE = 31;

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
        return MIN_EXPECTED_VISIT_DATE <= expectedVisitDate && expectedVisitDate <= MAX_EXPECTED_VISIT_DATE;
    }
}
