package pairmatching.domain;

import static pairmatching.exception.ExceptionMessage.NOT_FOUND_COMMAND;

import java.util.Arrays;

public enum Command {
    PAIR_MATCHING("페어 매칭", "1"),
    FIND_PAIR_MATCHING_RESULT("페어 조회", "2"),
    CLEAR_PAIR_MATCHING_RESULT("페어 초기화", "3"),
    QUIT("종료", "Q"),
    ;

    private final String commandName;
    private final String commandValue;

    Command(String commandName, String commandValue) {
        this.commandName = commandName;
        this.commandValue = commandValue;
    }

    public static Command getCommand(String inputCommand) {
        return Arrays.stream(values())
                .filter(controlCommand -> controlCommand.commandValue.equals(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_COMMAND.getMessage()));
    }

//    public boolean isContinue() {
//        return !this.equals(QUIT);
//    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandValue() {
        return commandValue;
    }

    public boolean isQuit() {
        return this.equals(QUIT);
    }
}
