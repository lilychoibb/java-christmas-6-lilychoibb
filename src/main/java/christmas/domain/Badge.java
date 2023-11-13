package christmas.domain;

public enum Badge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String name;
    private final int totalDiscountAmount;

    Badge(String name, int totalDiscountAmount) {
        this.name = name;
        this.totalDiscountAmount = totalDiscountAmount;
    }

    public String getName() {
        return name;
    }

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }
}