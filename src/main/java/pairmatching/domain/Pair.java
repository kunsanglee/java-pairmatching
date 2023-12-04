package pairmatching.domain;

import static pairmatching.exception.ExceptionMessage.INVALID_PAIR;

import java.util.List;

public class Pair {
    private final List<Crew> pair;

    public Pair(List<Crew> crews) {
        validate(crews);
        this.pair = crews;
    }

    public void validate(List<Crew> crews) {
        if (crews.stream().anyMatch(crew -> crew.isExPair(crews))) {
            throw new IllegalArgumentException(INVALID_PAIR.getMessage());
        }
    }

    public List<Crew> getPair() {
        return pair;
    }

    public void addExPair(List<Crew> crews) {
        this.pair.forEach(crew -> crew.addExPair(crews));
    }

    public void clearExPair() {
        this.pair.forEach(Crew::clearExPair);
    }
}
