package pairmatching.controller;

import java.util.EnumMap;
import java.util.Map;
import pairmatching.domain.MainOption;
import pairmatching.view.InputView;

public class MainController {
    private final Map<MainOption, SubController> subControllers = new EnumMap<>(MainOption.class);

    public MainController() {
        this.subControllers.put(MainOption.PAIR_MATCHING, new PairMatchingController());
        this.subControllers.put(MainOption.PAIR_FIND, new PairFindController());
        this.subControllers.put(MainOption.PAIR_INITIALIZE, new PairInitializeController());
        this.subControllers.put(MainOption.QUIT, new ExitController());
    }

    public void run() {
        while (true) {
            MainOption mainOption = InputView.readMainOption();
            SubController subController = subControllers.get(mainOption);
            if (subController.process().equals(MainOption.QUIT)) {
                break;
            }
        }
    }
}
