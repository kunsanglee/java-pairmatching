package pairmatching.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pairmatching.repository.CrewRepository;

public class CrewShufflerTest {
    @DisplayName("크루들의 명단을 섞는다.")
    @Test
    public void shuffle() throws Exception {
        List<String> crews = CrewRepository.getCrews(Mission.of(Course.BACKEND, Level.LEVEL_1, "로또"));
        List<String> shuffle = CrewShuffler.shuffle(crews);
        Assertions.assertThat(crews.size()).isEqualTo(shuffle.size()); // 원본 크기와 섞인 크기가 같은지 확인
        Assertions.assertThat(crews).containsAll(shuffle); // 원본 명단에 모든 요소가 있는지 확인
        Assertions.assertThat(crews).isNotEqualTo(shuffle); // 원본 명단과 섞인 명단이 다른지 확인
    }
}
