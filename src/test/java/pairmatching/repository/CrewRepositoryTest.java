package pairmatching.repository;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;

public class CrewRepositoryTest {

    @DisplayName("과정별 크루를 조회한다.")
    @ParameterizedTest
    @MethodSource("findCrewByCourseArguments")
    public void findCrewByCourse(Mission mission, List<String> expectedCrews) throws Exception {
        List<String> crews = CrewRepository.getCrews(mission);
        Assertions.assertThat(crews).isEqualTo(expectedCrews);
    }

    static Stream<Arguments> findCrewByCourseArguments() {
        return Stream.of(
                arguments(Mission.of(Course.BACKEND, Level.LEVEL_1, "로또"),
                        Arrays.asList("백호", "태웅", "치수", "태섭", "대만", "준호", "대협", "덕규", "태산", "경태", "수겸", "현준", "준섭",
                                "한나", "소연", "호열", "대남", "용팔", "구식", "달재")),
                arguments(Mission.of(Course.FRONTEND, Level.LEVEL_1, "로또"),
                        Arrays.asList("보노", "시저", "쉐리", "신디", "다비", "덴버", "이브", "제시", "라라", "린다", "리사", "니콜", "로드",
                                "윌터", "제키"
                        ))
        );
    }
}
