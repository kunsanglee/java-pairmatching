package pairmatching.controller;

import pairmatching.domain.MainOption;

public class PairMatchingController implements SubController {
    @Override
    public MainOption process() {
        return MainOption.PAIR_MATCHING;
    }
}
