package pairmatching.domain;

import static pairmatching.exception.ExceptionMessage.NOT_FOUND_COURSE;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드"),
    ;

    private final String name;

    public static Course of(String name) {
        return Arrays.stream(values())
                .filter(course -> course.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_COURSE.getMessage()));
    }

    Course(String name) {
        this.name = name;
    }
}
