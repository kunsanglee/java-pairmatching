package pairmatching.domain;

import java.util.Arrays;
import java.util.List;

public enum Level {
    LEVEL1("레벨1", Arrays.asList("자동차경주", "로또", "숫자야구게임")),
    LEVEL2("레벨2", Arrays.asList("장바구니", "결제", "지하철노선도")),
    LEVEL3("레벨3", Arrays.asList("")),
    LEVEL4("레벨4", Arrays.asList("성능개선", "배포")),
    LEVEL5("레벨5", Arrays.asList(""));

    private final String name;
    private final List<String> missions;

    Level(String name, List<String> missions) {
        this.name = name;
        this.missions = missions;
    }

    public static Level get(String inputLevel) {
        return Arrays.stream(values())
                .filter(level -> level.name.equals(inputLevel))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 레벨을 입력하세요."));
    }

    public String getName() {
        return this.name;
    }

    public List<String> getMission() {
        return this.missions;
    }

    // 추가 기능 구현
}
