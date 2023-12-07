package pairmatching.controller;

import pairmatching.domain.MainOption;

public class ExitController implements SubController {
    @Override
    public MainOption process() {
        return MainOption.QUIT;
    }
}
