package pairmatching.repository;

import static pairmatching.exception.ExceptionMessage.NOT_FOUND_CREW;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;

public abstract class CrewRepository {
    private final List<Crew> crews = new ArrayList<>();

    public void add(Course course, String name) {
        crews.add(new Crew(course, name));
    }

    public List<Crew> findAll() {
        return this.crews;
    }

    public Crew findByName(String name) {
        return this.crews.stream()
                .filter(crew -> crew.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_CREW.getMessage()));
    }
}
