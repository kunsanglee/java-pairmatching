package pairmatching.domain;

import java.util.Objects;

public class Mission {
    private final Course course;
    private final Level level;
    private final String name;

    public static Mission of(Course course, Level level, String name) {
        return new Mission(course, level, name);
    }

    public Mission(Course course, Level level, String name) {
        this.course = course;
        this.level = level;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mission)) {
            return false;
        }
        Mission mission = (Mission) o;
        return course == mission.course && level == mission.level && Objects.equals(name, mission.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level, name);
    }

    public boolean isFrontEnd() {
        return this.course.equals(Course.FRONTEND);
    }
}
