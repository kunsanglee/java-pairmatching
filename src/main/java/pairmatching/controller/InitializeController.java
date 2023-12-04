package pairmatching.controller;

import pairmatching.domain.Command;
import pairmatching.repository.MissionRepository;
import pairmatching.view.OutputView;

public class InitializeController implements SubController {
    private final MissionRepository missionRepository;
    private final OutputView outputView;

    public InitializeController(MissionRepository missionRepository, OutputView outputView) {
        this.missionRepository = missionRepository;
        this.outputView = outputView;
    }

    @Override
    public boolean isAcceptable(Command command) {
        return Command.CLEAR_PAIR_MATCHING_RESULT.equals(command);
    }

    @Override
    public void process() {
        missionRepository.clear();
        outputView.printClear();
    }
}
