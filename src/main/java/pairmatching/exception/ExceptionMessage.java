package pairmatching.exception;

public enum ExceptionMessage {
    PREFIX("[ERROR] "),
    NOT_FOUND_CREW_MD("크루 데이터 읽기 실패"),
    NOT_FOUND_COMMAND("존재하지 않는 옵션입니다."),
    NOT_FOUND_MISSION("존재하지 않는 미션입니다."),
    INVALID_PAIR_MATCHING_OPTION("과정, 레벨, 미션 이름을 잘못 입력하셨습니다."),
    INVALID_MISSION_INPUT("잘못된 미션 이름입니다."),
    INVALID_INPUT_YES_OR_NO("네 또는 아니오를 입력해주세요."),
    MATCHING_FAIL("매칭 실패"),
    INVALID_PAIR("이미 함께한 페어입니다."),
    NOT_FOUND_CREW("존재하지 않는 크루입니다."),
    NOT_FOUND_PAIRS("매칭 이력이 없습니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + message;
    }
}
