package pairmatching.dto;

import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Crew;
import pairmatching.domain.Pair;

public class PairsDto {
    public static final String DELIMITER = " : ";
    private final List<String> pairs;

    public PairsDto(List<Pair> pairs) {
        this.pairs = convertTo(pairs);
    }

    private static List<String> convertTo(List<Pair> pairs) {
        return pairs.stream()
                .map(PairsDto::getCrewName)
                .collect(Collectors.toList());
    }

    private static String getCrewName(Pair pair) {
        return pair.getPair().stream()
                .map(Crew::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    public List<String> getPairs() {
        return pairs;
    }
}
