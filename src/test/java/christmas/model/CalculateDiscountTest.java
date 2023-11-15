package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.Service;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @DisplayName("평일 할인을 올바르게 계산한다.")
    @Test
    void determineDiscountByWeekDayTest() {
        LocalDate date = LocalDate.of(2023, 12, 4);

        List<OrderedItem> expectedOrder = Arrays.asList(
                new OrderedItem("티본스테이크", 2),
                new OrderedItem("초코케이크", 1)
        );

        calculateDiscount.determineDiscountByDay(new Service(), date, expectedOrder);
        int actualValue = calculateDiscount.getTotalWeekDayDiscount();
        assertThat(actualValue).isEqualTo(-2023);
    }

    @DisplayName("주말 할인을 올바르게 계산한다.")
    @Test
    void determineDiscountByWeekendTest() {
        LocalDate date = LocalDate.of(2023, 12, 1);

        List<OrderedItem> expectedOrder = Arrays.asList(
                new OrderedItem("티본스테이크", 2),
                new OrderedItem("초코케이크", 1)
        );

        calculateDiscount.determineDiscountByDay(new Service(), date, expectedOrder);
        int actualValue = calculateDiscount.getTotalWeekendDiscount();
        assertThat(actualValue).isEqualTo(-4046);
    }
}