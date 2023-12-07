package pairmatching.controller;

import static pairmatching.exception.ExceptionMessage.NOT_FOUND_MATCH_RESULT;

import pairmatching.domain.MainOption;
import pairmatching.domain.Mission;
import pairmatching.domain.Pairs;
import pairmatching.repository.MissionRepository;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairFindController implements SubController {
    @Override
    public MainOption process() {
        OutputView.printCourseLevelMission();
        try {
            Mission findMission = InputView.readCourseLevelMission(); // 과정,레벨,미션을 입력받는다.
            Pairs pairs = MissionRepository.findPairsByMission(findMission); // 미션저장소에 저장된 페어를 찾는다.
            if (pairs.isEmpty()) {
                throw new IllegalArgumentException(NOT_FOUND_MATCH_RESULT.getMessage());
            }
            OutputView.printMatchedPair(pairs);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return MainOption.PAIR_FIND;
    }
}
