package pairmatching.domain;

import static pairmatching.exception.ExceptionMessage.INVALID_PAIR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pair {
    private static final int MIN_PAIR_SIZE = 2;
    private static final int MAX_PAIR_SIZE = 3;
    private final List<String> pair = new ArrayList<>();

    public Pair(List<String> pair) {
        validate(pair);
        this.pair.addAll(pair);
    }

    private void validate(List<String> pair) {
        if (pair.size() < MIN_PAIR_SIZE || pair.size() > MAX_PAIR_SIZE) {
            throw new IllegalArgumentException(INVALID_PAIR.getMessage());
        }
    }

    public List<String> getPair() {
        return Collections.unmodifiableList(pair);
    }
}
