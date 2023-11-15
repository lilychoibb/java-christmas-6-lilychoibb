package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculateDiscountTest {
    private static CalculateDiscount calculateDiscount;

    @BeforeAll
    static void ConstructCalculateDiscount() {
        calculateDiscount = new CalculateDiscount();
    }

    @DisplayName("25일 이후에는 크리스마스 디데이 할인이 적용되지 않는다.")
    @Test
    void afterChristmasDDayDiscountTest() {
        calculateDiscount.determineDiscountByDate(26);
        assertThat(calculateDiscount.getChristmasDDay()).isEqualTo(0);
    }

    @DisplayName("크리스마스 디데이 할인을 올바르게 계산한다.")
    @Test
    void christmasDDayDiscountTest() {
        calculateDiscount.determineDiscountByDate(3);
        assertThat(calculateDiscount.getChristmasDDay()).isEqualTo(-1200);
    }
}