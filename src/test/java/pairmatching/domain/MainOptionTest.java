package pairmatching.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MainOptionTest {

    @DisplayName("존재하는 옵션을 선택하면 에러가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "Q"})
    void selectOption(String option) {
        Assertions.assertThatCode(() -> MainOption.of(option))
                .doesNotThrowAnyException();
    }

    @DisplayName("존재하지 않는 옵션을 선택하면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", " 4", "quit"})
    void selectOptionFail(String option) {
        Assertions.assertThatCode(() -> MainOption.of(option))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
