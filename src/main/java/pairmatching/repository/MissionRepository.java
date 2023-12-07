package pairmatching.repository;

import static pairmatching.exception.ExceptionMessage.NOT_FOUND_MISSION;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pairs;

public class MissionRepository {
    private static final Map<Mission, Pairs> missionPairs = new HashMap<>();

    static {
        addMissionByCourse(Course.BACKEND);
        addMissionByCourse(Course.FRONTEND);
    }

    private static void addMissionByCourse(Course course) {
        missionPairs.put(new Mission(course, Level.LEVEL_1, "자동차경주"), new Pairs());
        missionPairs.put(new Mission(course, Level.LEVEL_1, "로또"), new Pairs());
        missionPairs.put(new Mission(course, Level.LEVEL_1, "숫자야구게임"), new Pairs());
        missionPairs.put(new Mission(course, Level.LEVEL_2, "장바구니"), new Pairs());
        missionPairs.put(new Mission(course, Level.LEVEL_2, "결제"), new Pairs());
        missionPairs.put(new Mission(course, Level.LEVEL_2, "지하철노선도"), new Pairs());
        missionPairs.put(new Mission(course, Level.LEVEL_4, "성능개선"), new Pairs());
        missionPairs.put(new Mission(course, Level.LEVEL_4, "배포"), new Pairs());
    }

    public static boolean isNotMatched(Mission mission) {
        return missionPairs.get(mission).isEmpty();
    }

    public static Pairs findPairsByMission(Mission mission) {
        return Optional.ofNullable(missionPairs.get(mission))
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MISSION.getMessage()));
    }
}
