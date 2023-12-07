package pairmatching.domain;

import static pairmatching.exception.ExceptionMessage.NOT_FOUND_LEVEL;

import java.util.Arrays;

public enum Level {
    LEVEL_1("레벨1"),
    LEVEL_2("레벨2"),
    LEVEL_3("레벨3"),
    LEVEL_4("레벨4"),
    LEVEL_5("레벨5"),
    ;

    private final String name;

    public static Level of(String name) {
        return Arrays.stream(values())
                .filter(level -> level.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_LEVEL.getMessage()));
    }

    Level(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
