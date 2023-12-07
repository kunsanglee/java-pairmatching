package pairmatching.controller;

import java.util.List;
import pairmatching.domain.CrewShuffler;
import pairmatching.domain.MainOption;
import pairmatching.domain.Mission;
import pairmatching.domain.Pairs;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MissionRepository;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController implements SubController {
    @Override
    public MainOption process() {
        OutputView.printCourseLevelMission(); // 과정 레벨 미션 출력.
        return matchPair();
    }

    private MainOption matchPair() {
        Mission findMission = InputView.readCourseLevelMission(); // 과정,레벨,미션을 입력받는다.
        Pairs pairs = MissionRepository.findPairsByMission(findMission); // 미션저장소에 저장된 페어를 찾는다.
        List<String> crews = findCrews(findMission);
        List<String> shuffledCrews = shuffle(crews); // 크루들을 섞는다.
        if (pairs.isEmpty()) {
            return matchShuffledPair(pairs, shuffledCrews);
        }
        // 매칭이력이 있으면 다시 매칭할지 입력받는다.
        if (InputView.readRematch()) { // 다시 매칭한다
            return matchShuffledPair(pairs, shuffledCrews);
        }
        // InputView.readCourseLevelMission()부터 다시 실행. 과정, 레벨, 미션을 선택하세요.
        return matchPair();
    }

    private static MainOption matchShuffledPair(Pairs pairs, List<String> shuffledCrews) {
        pairs.process(shuffledCrews);
        OutputView.printMatchedPair(pairs);
        return MainOption.PAIR_MATCHING;
    }

    private static List<String> shuffle(List<String> crews) {
        return CrewShuffler.shuffle(crews);
    }

    private static List<String> findCrews(Mission findMission) {
        List<String> crews = CrewRepository.getCrews(findMission); // 과정에 해당하는 크루들을 조회한다.
        return crews;
    }
}
