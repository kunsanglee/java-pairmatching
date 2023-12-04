package pairmatching.controller;

import static pairmatching.exception.ExceptionMessage.NOT_FOUND_PAIRS;

import pairmatching.domain.Command;
import pairmatching.domain.Mission;
import pairmatching.domain.Pairs;
import pairmatching.dto.PairsDto;
import pairmatching.repository.MissionRepository;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairFindController implements SubController {
    private final InputView inputView;
    private final OutputView outputView;
    private final MissionRepository missionRepository;

    public PairFindController(MissionRepository missionRepository, InputView inputView,
                              OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.missionRepository = missionRepository;
    }

    @Override
    public boolean isAcceptable(Command command) {
        return Command.FIND_PAIR_MATCHING_RESULT.equals(command);
    }

    @Override
    public void process() {
        outputView.printCourseLevelMission();
        Mission mission = Mission.of(inputView.getPairMatchingOption());
        if (missionRepository.isPairsEmpty(mission)) {
            throw new IllegalArgumentException(NOT_FOUND_PAIRS.getMessage());
        }
        Pairs pairMatchResult = missionRepository.getPairMatchResult(mission);
        outputView.printPairs(new PairsDto(pairMatchResult.getPairs()));
    }
}
