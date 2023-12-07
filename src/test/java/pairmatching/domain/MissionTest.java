package pairmatching.domain;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MissionTest {

    @DisplayName("미션을 생성한다.")
    @Test
    public void create() throws Exception {
        Assertions.assertThatCode(() -> new Mission(Course.BACKEND, Level.LEVEL_1, "로또"))
                .doesNotThrowAnyException();
    }

    @DisplayName("조회한 미션이 프론트엔드인지 반환한다.")
    @ParameterizedTest
    @MethodSource("isFrontendArguments")
    public void isFrontend(Mission mission, boolean expected) throws Exception {
        Assertions.assertThat(mission.isFrontEnd()).isEqualTo(expected);
    }

    static Stream<Arguments> isFrontendArguments() {
        return Stream.of(
                arguments(new Mission(Course.FRONTEND, Level.LEVEL_1, "로또"), true),
                arguments(new Mission(Course.BACKEND, Level.LEVEL_1, "로또"), false)
        );
    }
}
