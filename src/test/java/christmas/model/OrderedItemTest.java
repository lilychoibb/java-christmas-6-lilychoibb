package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderedItemTest {
    @DisplayName("메뉴판에 없는 메뉴를 주문한 경우 예외가 발생한다.")
    @Test
    void isNotExistedItemTest() {
        assertThatThrownBy(() -> new OrderedItem("짜장면", 1)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴의 개수가 1개 이상이 아닌 경우 예외가 발생한다.")
    @Test
    void isInvalidCountItemTest() {
        assertThatThrownBy(() -> new OrderedItem("티본스테이크", 0)).isInstanceOf(IllegalArgumentException.class);
    }
}