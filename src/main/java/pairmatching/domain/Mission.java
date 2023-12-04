package pairmatching.domain;

import java.util.Objects;
import pairmatching.dto.OptionDto;

public class Mission {
    private final Course course;
    private final Level level;
    private final String name;

    public static Mission of(OptionDto optionDto) {
        return new Mission(optionDto.getCourse(), optionDto.getLevel(), optionDto.getMission());
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
}
