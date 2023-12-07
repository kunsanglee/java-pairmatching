package pairmatching.domain;

import static pairmatching.exception.ExceptionMessage.NOT_FOUND_MAIN_OPTION;

import java.util.Arrays;

public enum MainOption {
    PAIR_MATCHING("1"),
    PAIR_FIND("2"),
    PAIR_INITIALIZE("3"),
    QUIT("Q"),
    ;

    private final String option;

    MainOption(String option) {
        this.option = option;
    }

    public static MainOption of(String option) {
        return Arrays.stream(values())
                .filter(mainOption -> mainOption.option.equals(option))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MAIN_OPTION.getMessage()));
    }

    public boolean isQuit() {
        return this.equals(QUIT);
    }
}
