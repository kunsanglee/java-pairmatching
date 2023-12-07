package pairmatching.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PairsTest {

    @DisplayName("페어묶음을 생성한다.")
    @Test
    public void createPairs() throws Exception {
        Assertions.assertThatCode(() -> new Pairs())
                .doesNotThrowAnyException();
    }
}
