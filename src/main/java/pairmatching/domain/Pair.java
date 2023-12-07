package pairmatching.domain;

import static pairmatching.exception.ExceptionMessage.INVALID_PAIR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pair {
    private final List<String> pair = new ArrayList<>();

    public Pair(List<String> pair) {
        validate(pair);
        this.pair.addAll(pair);
    }

    private void validate(List<String> pair) {
        if (pair.size() < 2 || pair.size() > 3) {
            throw new IllegalArgumentException(INVALID_PAIR.getMessage());
        }
    }

    public List<String> getPair() {
        return Collections.unmodifiableList(pair);
    }
}
