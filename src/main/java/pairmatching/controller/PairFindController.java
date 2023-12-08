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
            Mission findMission = InputView.readCourseLevelMission();
            Pairs pairs = MissionRepository.findPairsByMission(findMission);
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
