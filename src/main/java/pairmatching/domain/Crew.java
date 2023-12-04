package pairmatching.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Crew {
    private Course course;
    private String name;
    private Set<Crew> exPairs = new HashSet<>();

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addExPair(List<Crew> crews) {
        crews.stream()
                .filter(crew -> !crew.equals(this))
                .forEach(crew -> this.exPairs.add(crew));
    }

    public boolean isExPair(List<Crew> crews) {
        return crews.stream().filter(crew -> !crew.equals(this)).anyMatch(crew -> this.exPairs.contains(crew));
    }

    public void clearExPair() {
        this.exPairs.clear();
    }
}
