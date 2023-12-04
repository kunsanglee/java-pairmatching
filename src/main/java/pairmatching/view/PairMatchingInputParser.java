package pairmatching.view;

import static pairmatching.exception.ExceptionMessage.INVALID_PAIR_MATCHING_OPTION;

import java.util.Arrays;
import java.util.List;
import pairmatching.dto.OptionDto;

public class PairMatchingInputParser {

    public static final String DELIMITER = ", ";
    public static final int EXACT_SIZE = 3;
    public static final int LIMIT = -1;

    public OptionDto parsePairMatchingOption(String inputOption) {
        List<String> result = Arrays.asList(inputOption.split(DELIMITER, LIMIT));
        if (result.size() != EXACT_SIZE) {
            throw new IllegalArgumentException(INVALID_PAIR_MATCHING_OPTION.getMessage());
        }
        return new OptionDto(result);
    }
}
