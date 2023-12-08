package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pairs {
    private final List<Pair> pairs = new ArrayList<>();

    public boolean isEmpty() {
        return this.pairs.isEmpty();
    }

    public void process(List<String> shuffledCrews) {
        int size = shuffledCrews.size();
        int pairSize = size / 2 * 2;
        for (int i = 0; i < pairSize; i += 2) {
            this.add(new Pair(shuffledCrews.subList(i, i + 2)));
        }
        if (size > pairSize) {
            int matchedPairSize = this.pairs.size();
            Pair pair = this.pairs.get(matchedPairSize - 1);
            pair = pair.add(shuffledCrews.get(matchedPairSize - 1));
        }
    }

    private void add(Pair pair) {
        this.pairs.add(pair);
    }

    public List<Pair> getPairs() {
        return Collections.unmodifiableList(this.pairs);
    }

    public void clear() {
        this.pairs.clear();
    }
}
