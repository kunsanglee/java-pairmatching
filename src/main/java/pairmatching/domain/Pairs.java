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
        if (shuffledCrews.size() % 2 != 0) {
            // 홀수면 마지막 페어에 3명
            for (int i = 0; i < shuffledCrews.size() - 3; i += 2) {
                Pair pair = new Pair(shuffledCrews.subList(i, i + 2));
                this.add(pair);
            }
            this.add(new Pair(shuffledCrews.subList(shuffledCrews.size() - 3, shuffledCrews.size())));
            return;
        }
        // 짝수면 2명씩 페어
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
