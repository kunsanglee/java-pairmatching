package pairmatching.controller;

import static pairmatching.retry.Retry.retryGetInput;

import pairmatching.domain.Command;
import pairmatching.dto.OptionDto;
import pairmatching.service.PairMatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController implements SubController {
    private final InputView inputView;
    private final OutputView outputView;
    private final PairMatchingService pairMatchingService;

    public PairMatchingController(InputView inputView, OutputView outputView,
                                  PairMatchingService pairMatchingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairMatchingService = pairMatchingService;
    }

    @Override
    public boolean isAcceptable(Command command) {
        return Command.PAIR_MATCHING.equals(command);
    }

    @Override
    public void process() {
        outputView.printCourseLevelMission();
        OptionDto pairMatchingOption = retryGetInput(inputView::getPairMatchingOption);
        if (pairMatchingService.isPairsEmpty(pairMatchingOption)) {
            printPairs(pairMatchingOption);
            return;
        }
        if (inputView.getExistPairsOrNot()) {

            printPairs(pairMatchingOption);
        }
    }

    private void printPairs(OptionDto pairMatchingOption) {
        pairMatchingService.match(pairMatchingOption);
        outputView.printPairs(pairMatchingService.getPairs(pairMatchingOption));
    }
}
