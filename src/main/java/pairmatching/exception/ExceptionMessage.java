package pairmatching.exception;

public enum ExceptionMessage {
    NOT_FOUND_COURSE("존재하지 않는 과정입니다."),
    NOT_FOUND_LEVEL("존재하지 않는 레벨입니다."),
    NOT_FOUND_MISSION("존재하지 않는 미션입니다."),
    NOT_FOUND_MAIN_OPTION("존재하지 않는 옵션입니다."),
    INVALID_PAIR("잘못된 페어입니다."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ExceptionMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
