package christmas.model;

public record ExpectedVisitDate(int expectedVisitDate) {
    public static final int MIN_EXPECTED_VISIT_DATE = 1;
    public static final int MAX_EXPECTED_VISIT_DATE = 31;

    public ExpectedVisitDate {
        validator(expectedVisitDate);
    }

    private void validator(int expectedVisitDate) {
        if (!validateNumberRange(expectedVisitDate)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean validateNumberRange(int expectedVisitDate) {
        return MIN_EXPECTED_VISIT_DATE <= expectedVisitDate && expectedVisitDate <= MAX_EXPECTED_VISIT_DATE;
    }
}