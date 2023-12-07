package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class CrewShuffler {

    public static List<String> shuffle(List<String> crew) {
        return Randoms.shuffle(crew);
    }
}
