package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PairTest {

    @DisplayName("페어를 생성한다.")
    @ParameterizedTest
    @MethodSource("createArguments")
    public void create(List<String> crews) throws Exception {
        Assertions.assertThatCode(() -> new Pair(crews))
                .doesNotThrowAnyException();
    }

    static Stream<Arguments> createArguments() {
        return Stream.of(
                Arguments.arguments(Arrays.asList("가", "나")),
                Arguments.arguments(Arrays.asList("가", "나", "다"))
        );
    }

    @DisplayName("1명 이하거나 4명 이상의 인원을 페어로 구성시 에러가 발생한다.")
    @ParameterizedTest
    @MethodSource("createFailArguments")
    public void createFail(List<String> crews) throws Exception {
        Assertions.assertThatCode(() -> new Pair(crews))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> createFailArguments() {
        return Stream.of(
                Arguments.arguments(Arrays.asList("가")),
                Arguments.arguments(Arrays.asList("가", "나", "다", "라"))
        );
    }
}
