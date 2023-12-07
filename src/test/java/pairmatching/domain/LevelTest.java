package pairmatching.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LevelTest {

    @DisplayName("입력한 레벨이 존재하면 에러가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"레벨1", "레벨2", "레벨3", "레벨4", "레벨5"})
    public void validateLevelSuccess(String input) throws Exception {
        Assertions.assertThatCode(() -> Level.of(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("입력한 레벨이 존재하지 않으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "123", "level", "easy", "어려움"})
    public void validateLevelFail(String input) throws Exception {
        Assertions.assertThatCode(() -> Level.of(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
