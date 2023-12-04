package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class CrewShuffler {

    public List<String> shuffle(List<String> crews) {
        return Randoms.shuffle(crews);
    }
}
