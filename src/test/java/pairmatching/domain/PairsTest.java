package pairmatching.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pairmatching.repository.CrewRepository;

public class PairsTest {

    @DisplayName("페어묶음을 생성한다.")
    @Test
    public void createPairs() throws Exception {
        Assertions.assertThatCode(() -> new Pairs())
                .doesNotThrowAnyException();
    }

    @DisplayName("크루의 수에 맞게 페어를 구성해서 넣는다.")
    @Test
    public void process() throws Exception {
        Pairs pairs = new Pairs();
        Assertions.assertThat(pairs.isEmpty()).isTrue();
        List<String> crews = CrewRepository.getCrews(Mission.of(Course.BACKEND, Level.LEVEL_1, "로또"));
        pairs.process(crews);
        Assertions.assertThat(pairs.isEmpty()).isFalse();
    }

    @DisplayName("페어묶음을 비운다.")
    @Test
    public void clear() throws Exception {
        Pairs pairs = new Pairs();
        List<String> crews = CrewRepository.getCrews(Mission.of(Course.BACKEND, Level.LEVEL_1, "로또"));
        pairs.process(crews);
        Assertions.assertThat(pairs.isEmpty()).isFalse();

        pairs.clear();
        Assertions.assertThat(pairs.isEmpty()).isTrue();
    }

    @DisplayName("페어의 명단을 가져온다.")
    @Test
    public void get() throws Exception {
        Pairs pairs = new Pairs();
        List<String> crews = CrewRepository.getCrews(Mission.of(Course.BACKEND, Level.LEVEL_1, "로또"));
        pairs.process(crews);
        List<Pair> findPairs = pairs.getPairs();
        findPairs.forEach(pair -> pair.getPair()
                .forEach(crew -> Assertions.assertThat(crews.contains(crew))
                        .isTrue())
        );
    }
}
