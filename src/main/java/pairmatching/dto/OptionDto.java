package pairmatching.dto;

import static pairmatching.exception.ExceptionMessage.INVALID_MISSION_INPUT;

import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Level;

public class OptionDto {
    private final Course course;
    private final Level level;
    private final String mission;

    public OptionDto(List<String> options) {
        this.course = Course.get(options.get(0));
        this.level = Level.get(options.get(1));
        this.mission = this.level.getMission().stream()
                .filter(levelMission -> levelMission.equals(options.get(2)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_MISSION_INPUT.getMessage()));
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public String getMission() {
        return mission;
    }
}
