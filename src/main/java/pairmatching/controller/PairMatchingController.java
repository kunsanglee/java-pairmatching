package pairmatching.controller;

import static pairmatching.controller.Retry.retry;

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
        OutputView.printCourseLevelMission();
        try {
            return matchPair();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return matchPair();
        }
    }

    private MainOption matchPair() {
        Mission findMission = retry(InputView::readCourseLevelMission);
        Pairs pairs = MissionRepository.findPairsByMission(findMission);
        List<String> crews = findCrews(findMission);
        List<String> shuffledCrews = shuffle(crews);
        if (pairs.isEmpty()) {
            return matchShuffledPair(pairs, shuffledCrews);
        }
        if (retry(InputView::readRematch)) {
            return matchShuffledPair(pairs, shuffledCrews);
        }
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
        return CrewRepository.getCrews(findMission);
    }
}
