package pairmatching.view;

import static pairmatching.exception.ExceptionMessage.INVALID_COURSE_LEVEL_MISSION;
import static pairmatching.exception.ExceptionMessage.NOT_FOUND_MAIN_OPTION;
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

    public static MainOption readMainOption() {
        System.out.println("기능을 선택하세요.");
        System.out.println(MAIN_OPTION.getMessage());
        try {
            return MainOption.of(Console.readLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readMainOption();
        }
    }

    public static Mission readCourseLevelMission() {
        System.out.println(READ_COURSE_LEVEL_MISSION.getMessage());
        try {
            String input = Console.readLine();
            List<String> parsedInput = Arrays.asList(input.split(", "));
            if (parsedInput.size() != 3) {
                throw new IllegalArgumentException(INVALID_COURSE_LEVEL_MISSION.getMessage());
            }
            return Mission.of(Course.of(parsedInput.get(0)), Level.of(parsedInput.get(1)), parsedInput.get(2));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readCourseLevelMission();
        }
    }

    public static boolean readRematch() {
        try {
            System.out.println(REMATCH.getMessage());
            return Rematch.REMATCH.isRematch(Console.readLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readRematch();
        }
    }

    protected enum InputMessage {
        MAIN_OPTION("1. 페어 매칭\n"
                + "2. 페어 조회\n"
                + "3. 페어 초기화\n"
                + "Q. 종료"),
        READ_COURSE_LEVEL_MISSION("과정, 레벨, 미션을 선택하세요.\n"
                + "ex) 백엔드, 레벨1, 자동차경주"),
        REMATCH("매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
                + "네 | 아니오"),
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
