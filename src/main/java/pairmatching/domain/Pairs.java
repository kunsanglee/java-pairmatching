package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Pairs {
    private final List<Pair> pairs = new ArrayList<>();

    public boolean isEmpty() {
        return this.pairs.isEmpty();
    }
}
