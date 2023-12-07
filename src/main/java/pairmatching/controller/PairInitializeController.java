package pairmatching.controller;

import pairmatching.domain.MainOption;

public class PairInitializeController implements SubController {
    @Override
    public MainOption process() {
        return MainOption.PAIR_INITIALIZE;
    }
}
