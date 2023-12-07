package pairmatching.controller;

import pairmatching.domain.MainOption;
import pairmatching.repository.MissionRepository;
import pairmatching.view.OutputView;

public class PairInitializeController implements SubController {
    @Override
    public MainOption process() {
        OutputView.printClear();
        MissionRepository.clear();
        return MainOption.PAIR_INITIALIZE;
    }
}
