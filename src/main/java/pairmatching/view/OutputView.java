package pairmatching.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.dto.PairsDto;

public class OutputView {
    public static final String COURSES = "과정: %s";
    public static final String MISSION = "미션:";
    public static final String LEVEL_NAME_AND_MISSIONS = "  - %s: %s";
    public static final String DELIMITER = " | ";
    public static final String PREFIX_AND_SUFFIX = "#############################################";
    public static final String INITIALIZE_MESSAGE = "초기화 되었습니다.";

    public void printCourseLevelMission() {
        System.out.println();
        System.out.println(PREFIX_AND_SUFFIX);
        System.out.println(String.format(COURSES, Arrays.stream(Course.values()).map(Course::getName).collect(
                Collectors.joining(DELIMITER))));
        System.out.println(MISSION);
        Arrays.stream(Level.values()).forEach(OutputView::printByLevel);
        System.out.println(PREFIX_AND_SUFFIX);
        System.out.println();
    }

    private static void printByLevel(Level level) {
        System.out.println(
                String.format(LEVEL_NAME_AND_MISSIONS, level.getName(), String.join(DELIMITER, level.getMission())));
    }

    public void printPairs(PairsDto pairsDto) {
        List<String> pairs = pairsDto.getPairs();
        pairs.forEach(System.out::println);
        System.out.println();
    }

    public void printClear() {
        System.out.println(INITIALIZE_MESSAGE);
    }
}
