package pairmatching.controller;

import pairmatching.domain.Command;

public interface SubController {
    boolean isAcceptable(Command command);

    void process();

}
