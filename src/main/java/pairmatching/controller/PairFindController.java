package pairmatching.controller;

import pairmatching.domain.MainOption;

public class PairFindController implements SubController {
    @Override
    public MainOption process() {
        return MainOption.PAIR_FIND;
    }
}
