package pairmatching.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MissionTest {

    @DisplayName("미션을 생성한다.")
    @Test
    public void create() throws Exception {
        Assertions.assertThatCode(() -> new Mission(Course.BACKEND, Level.LEVEL_1, "로또"))
                .doesNotThrowAnyException();
    }
}
