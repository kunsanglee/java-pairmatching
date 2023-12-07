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
        if (isCrewSizeOdd(shuffledCrews)) {
            matchPairForOdd(shuffledCrews);
            return;
        }
        matchPairForEven(shuffledCrews);
    }

    private static boolean isCrewSizeOdd(List<String> shuffledCrews) {
        return shuffledCrews.size() % 2 != 0;
    }

    private void matchPairForOdd(List<String> shuffledCrews) {
        for (int i = 0; i < shuffledCrews.size() - 3; i += 2) {
            Pair pair = new Pair(shuffledCrews.subList(i, i + 2));
            this.add(pair);
        }
        this.add(new Pair(shuffledCrews.subList(shuffledCrews.size() - 3, shuffledCrews.size())));
    }

    private void matchPairForEven(List<String> shuffledCrews) {
        for (int i = 0; i < shuffledCrews.size(); i += 2) {
            this.add(new Pair(shuffledCrews.subList(i, i + 2)));
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
