package pairmatching.view;

import static pairmatching.exception.ExceptionMessage.INVALID_INPUT_YES_OR_NO;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.dto.OptionDto;

public class InputView {
    public static final String YES = "네";
    public static final String NO = "아니오";
    public static final String REMATCH_MESSAGE = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
            + "네 | 아니오";
    private final PairMatchingInputParser inputParser;

    public InputView(PairMatchingInputParser inputParser) {
        this.inputParser = inputParser;
    }

    public OptionDto getPairMatchingOption() {
        return inputParser.parsePairMatchingOption(Console.readLine());
    }

    public boolean getExistPairsOrNot() {
        System.out.println(REMATCH_MESSAGE);
        String decision = Console.readLine();
        if (!decision.equals(YES) && !decision.equals(NO)) {
            throw new IllegalArgumentException(INVALID_INPUT_YES_OR_NO.getMessage());
        }
        return decision.equals(YES);
    }
}
