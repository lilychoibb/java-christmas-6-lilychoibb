package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ServiceTest {
    private static Service service;

    @BeforeAll
    static void constructService() {
        service = new Service();
    }

    @DisplayName("날짜 입력 값이 공백이면 에러가 발생한다.")
    @Test
    void isEmptyDataTest() {
        assertThatThrownBy(() -> service.isValidData("")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("날짜 입력 값이 숫자가 아니면 에러가 발생한다.")
    @Test
    void isNotNumericDataTest() {
        assertThatThrownBy(() -> service.isValidData("일")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력 값의 공백을 제거한다.")
    @Test
    void removeBlankTest() {
        assertThat(service.removeBlank("티본스테이크-1, 바비큐립-1")).isEqualTo("티본스테이크-1,바비큐립-1");
    }

