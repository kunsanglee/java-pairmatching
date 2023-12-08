package pairmatching.view;

import static pairmatching.exception.ExceptionMessage.INVALID_COURSE_LEVEL_MISSION;
import static pairmatching.exception.ExceptionMessage.NOT_FOUND_MAIN_OPTION;
import static pairmatching.view.InputView.InputMessage.COURSE_LEVEL_MISSION_DELIMITER;
import static pairmatching.view.InputView.InputMessage.INPUT_MAIN_OPTION;
import static pairmatching.view.InputView.InputMessage.MAIN_OPTION;
import static pairmatching.view.InputView.InputMessage.READ_COURSE_LEVEL_MISSION;
import static pairmatching.view.InputView.InputMessage.REMATCH;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.MainOption;
import pairmatching.domain.Mission;

public class InputView {

    private static final int COURSE_LEVEL_MISSION_INPUT_SIZE = 3;
    private static final int COURSE_INDEX = 0;
    private static final int LEVEL_INDEX = 1;
    private static final int MISSION_INDEX = 2;

    public static MainOption readMainOption() {
        System.out.println(INPUT_MAIN_OPTION.getMessage());
        System.out.println(MAIN_OPTION.getMessage());
        return MainOption.of(Console.readLine());
    }

    public static Mission readCourseLevelMission() {
        System.out.println(READ_COURSE_LEVEL_MISSION.getMessage());
        String input = Console.readLine();
        List<String> parsedInput = Arrays.asList(input.split(COURSE_LEVEL_MISSION_DELIMITER.getMessage()));
        validateInputSize(parsedInput);
        return Mission.of(Course.of(parsedInput.get(COURSE_INDEX)),
                Level.of(parsedInput.get(LEVEL_INDEX)),
                parsedInput.get(MISSION_INDEX));
    }

    private static void validateInputSize(List<String> parsedInput) {
        if (parsedInput.size() != COURSE_LEVEL_MISSION_INPUT_SIZE) {
            throw new IllegalArgumentException(INVALID_COURSE_LEVEL_MISSION.getMessage());
        }
    }

    public static boolean readRematch() {
        System.out.println(REMATCH.getMessage());
        return Rematch.REMATCH.isRematch(Console.readLine());
    }

    protected enum InputMessage {
        INPUT_MAIN_OPTION("기능을 선택하세요."),
        MAIN_OPTION("1. 페어 매칭\n"
                + "2. 페어 조회\n"
                + "3. 페어 초기화\n"
                + "Q. 종료"),
        READ_COURSE_LEVEL_MISSION("과정, 레벨, 미션을 선택하세요.\n"
                + "ex) 백엔드, 레벨1, 자동차경주"),
        REMATCH("매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
                + "네 | 아니오"),
        COURSE_LEVEL_MISSION_DELIMITER(", "),

        ;

        private final String message;

        InputMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    protected enum Rematch {
        REMATCH("네"),
        QUIT("아니오"),
        ;
        private final String message;

        Rematch(String message) {
            this.message = message;
        }

        public boolean isRematch(String input) {
            Rematch findOption = findRematchOption(input);
            return findOption.equals(REMATCH);
        }

        private static Rematch findRematchOption(String input) {
            return Arrays.stream(values())
                    .filter(option -> option.message.equals(input))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MAIN_OPTION.getMessage()));
        }
    }
}
