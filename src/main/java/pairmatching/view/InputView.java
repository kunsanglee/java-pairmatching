package pairmatching.view;

import static pairmatching.view.InputView.InputMessage.MAIN_OPTION;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.domain.MainOption;

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

    protected enum InputMessage {
        MAIN_OPTION("1. 페어 매칭\n"
                + "2. 페어 조회\n"
                + "3. 페어 초기화\n"
                + "Q. 종료");

        private final String message;

        InputMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
