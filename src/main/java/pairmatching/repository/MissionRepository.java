package pairmatching.repository;

import static pairmatching.exception.ExceptionMessage.NOT_FOUND_MISSION;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import pairmatching.domain.Mission;
import pairmatching.domain.Pairs;
import pairmatching.dto.OptionDto;

public class MissionRepository {
    private final Map<Mission, Pairs> missionPairs = new LinkedHashMap<>();

    public void add(Mission mission, Pairs pairs) {
        this.missionPairs.put(mission, pairs);
    }

    public boolean isPairsEmpty(Mission mission) {
        return this.missionPairs.get(mission).isPairsEmpty();
    }

    public Pairs findByMission(Mission mission) {
        return Optional.ofNullable(this.missionPairs.get(mission))
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MISSION.getMessage()));
    }

    public Pairs getPairMatchResult(Mission mission) {
        return this.missionPairs.get(mission);
    }

    public void clearSpecificMission(OptionDto optionDto) {
        this.missionPairs.get(Mission.of(optionDto)).clear();
    }

    public void clear() {
        this.missionPairs.forEach((mission, pairs) -> {
            pairs.clear();
            missionPairs.put(mission, new Pairs());
        });
    }
}
