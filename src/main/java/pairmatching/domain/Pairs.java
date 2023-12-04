package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Pairs {
    private final List<Pair> pairs = new ArrayList<>();

    public boolean isPairsEmpty() {
        return this.pairs.isEmpty();
    }

    public List<Pair> getPairs() {
        return this.pairs;
    }

    public void addAll(List<Pair> pairs) {
        this.pairs.addAll(pairs);
        addEachCrewExPair();
    }

    private void addEachCrewExPair() {
        this.pairs.forEach(pair -> pair.addExPair(pair.getPair()));
    }

    public void clear() {
        clearEachCrewExPair();
        this.pairs.clear();
    }

    private void clearEachCrewExPair() {
        this.pairs.forEach(Pair::clearExPair);
    }
}
