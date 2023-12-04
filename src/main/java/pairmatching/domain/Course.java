package pairmatching.domain;

import java.util.Arrays;
import pairmatching.config.AppConfig;
import pairmatching.repository.CrewRepository;

public enum Course {
    BACKEND("백엔드", AppConfig.backendCrewRepository()),
    FRONTEND("프론트엔드", AppConfig.frontendCrewRepository());

    private String name;
    private final CrewRepository crewRepository;

    Course(String name, CrewRepository crewRepository) {
        this.name = name;
        this.crewRepository = crewRepository;
    }

    public static Course get(String inputCourse) {
        return Arrays.stream(values())
                .filter(course -> course.name.equals(inputCourse))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 과정을 입력하세요."));
    }

    public static CrewRepository getCrewRepository(Course course) {
        return course.crewRepository;
    }

    public String getName() {
        return name;
    }
}
