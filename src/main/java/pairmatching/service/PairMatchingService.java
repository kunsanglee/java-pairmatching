package pairmatching.service;

import static pairmatching.exception.ExceptionMessage.MATCHING_FAIL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.CrewShuffler;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.domain.Pairs;
import pairmatching.dto.OptionDto;
import pairmatching.dto.PairsDto;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MissionRepository;

public class PairMatchingService {
    public static final int RETRY_COUNT = 3;
    public static final String RETRY_COUNT_MESSAGE = "재시도 카운트: %d";
    private final CrewShuffler crewShuffler;
    private final MissionRepository missionRepository;

    public PairMatchingService(CrewShuffler crewShuffler, MissionRepository missionRepository) {
        this.crewShuffler = crewShuffler;
        this.missionRepository = missionRepository;
    }

    public boolean isPairsEmpty(OptionDto optionDto) {
        return missionRepository.isPairsEmpty(
                new Mission(optionDto.getCourse(), optionDto.getLevel(), optionDto.getMission()));
    }

    public void match(OptionDto optionDto) {
        missionRepository.clearSpecificMission(optionDto);
        Pairs missionPairs = missionRepository.findByMission(Mission.of(optionDto));
        CrewRepository crewRepository = Course.getCrewRepository(optionDto.getCourse());
        List<Crew> crews = crewRepository.findAll();
        List<String> crewNames = crews.stream().map(Crew::getName).collect(Collectors.toList());
        if (isMatchSuccess(crewNames, crewRepository, missionPairs)) {
            return;
        }
        throw new IllegalArgumentException(MATCHING_FAIL.getMessage());
    }

    private boolean isMatchSuccess(List<String> crewNames, CrewRepository crewRepository, Pairs missionPairs) {
        for (int retryCount = 0; retryCount < RETRY_COUNT; retryCount++) {
            try {
                List<String> shuffledCrewNames = this.crewShuffler.shuffle(crewNames);
                List<Crew> shuffledCrews = shuffledCrewNames.stream()
                        .map(crewRepository::findByName)
                        .collect(Collectors.toList());
                missionPairs.addAll(collectPair(shuffledCrews, missionPairs));
                return true;
            } catch (IllegalArgumentException e) {
                System.out.println(String.format(RETRY_COUNT_MESSAGE, retryCount));
            }
        }
        return false;
    }

    private static List<Pair> collectPair(List<Crew> shuffledCrews, Pairs missionPairs) {
        int crewSize = shuffledCrews.size();
        if (crewSize % 2 == 0) {
            List<Pair> pairs = new ArrayList<>();
            for (int index = 0; index < crewSize; index += 2) {
                pairs.add(new Pair(shuffledCrews.subList(index, index + 2)));
            }
            return pairs;
        }
        List<Pair> pairs = new ArrayList<>();
        for (int index = 0; index < crewSize - 3; index += 2) {
            pairs.add(new Pair(shuffledCrews.subList(index, index + 2)));
        }
        pairs.add(new Pair(shuffledCrews.subList(crewSize - 3, crewSize)));
        missionPairs.addAll(pairs);
        return pairs;
    }

    public PairsDto getPairs(OptionDto optionDto) {
        Pairs pairMatchResult = missionRepository.getPairMatchResult(Mission.of(optionDto));
        return new PairsDto(pairMatchResult.getPairs());
    }
}
