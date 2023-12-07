package pairmatching.repository;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;

public class MissionRepositoryTest {

    @DisplayName("존재하는 미션을 조회시 에러가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("findArguments")
    public void find(Mission mission) throws Exception {
        Assertions.assertThatCode(() -> MissionRepository.findPairsByMission(mission))
                .doesNotThrowAnyException();
    }

    static Stream<Arguments> findArguments() {
        return Stream.of(
                arguments(new Mission(Course.BACKEND, Level.LEVEL_1, "자동차경주")),
                arguments(new Mission(Course.BACKEND, Level.LEVEL_1, "로또")),
                arguments(new Mission(Course.BACKEND, Level.LEVEL_1, "숫자야구게임"))
        );
    }

    @DisplayName("존재하지 않는 미션을 조회시 에러가 발생한다.")
    @ParameterizedTest
    @MethodSource("findFailArguments")
    public void findFail(Mission mission) throws Exception {
        Assertions.assertThatCode(() -> MissionRepository.findPairsByMission(mission))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> findFailArguments() {
        return Stream.of(
                arguments(new Mission(Course.BACKEND, Level.LEVEL_1, "없는거")),
                arguments(new Mission(Course.BACKEND, Level.LEVEL_1, "김치")),
                arguments(new Mission(Course.BACKEND, Level.LEVEL_1, "커피"))
        );
    }
}
