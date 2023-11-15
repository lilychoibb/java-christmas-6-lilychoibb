package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExpectedVisitDateTest {
    @DisplayName("예상 방문 날짜가 올바르게 입력되었는지 예외 테스트")
    @Test
    void expectedVisitDateTest() {
        assertThatThrownBy(() -> new ExpectedVisitDate(32)).isInstanceOf(IllegalArgumentException.class);
    }
}