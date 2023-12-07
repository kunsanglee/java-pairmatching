package pairmatching.view;

import static pairmatching.view.OutputView.OutputMessage.COURSE_LEVEL_MISSION;
import static pairmatching.view.OutputView.OutputMessage.PAIR_DELIMITER;
import static pairmatching.view.OutputView.OutputMessage.PAIR_RESULT;

import java.util.stream.Collectors;
import pairmatching.domain.Pairs;

public class OutputView {

    public static void printCourseLevelMission() {
        System.out.println(COURSE_LEVEL_MISSION.getMessage());
    }

    public static void printMatchedPair(Pairs pairs) {
        System.out.println(PAIR_RESULT.getMessage());
        pairs.getPairs().forEach(pair -> System.out.println(pair.getPair().stream().collect(
                Collectors.joining(PAIR_DELIMITER.getMessage()))));
    }

    protected enum OutputMessage {
        COURSE_LEVEL_MISSION("#############################################\n"
                + "과정: 백엔드 | 프론트엔드\n"
                + "미션:\n"
                + "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n"
                + "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n"
                + "  - 레벨3: \n"
                + "  - 레벨4: 성능개선 | 배포\n"
                + "  - 레벨5: \n"
                + "############################################\n"),
        PAIR_RESULT("페어 매칭 결과입니다."),
        PAIR_DELIMITER(" : "),
        ;

        private final String message;

        OutputMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
