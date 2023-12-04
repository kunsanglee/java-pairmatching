package pairmatching.config;

import static pairmatching.exception.ExceptionMessage.NOT_FOUND_CREW_MD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import pairmatching.controller.InitializeController;
import pairmatching.controller.PairFindController;
import pairmatching.controller.PairMatchingController;
import pairmatching.controller.SubController;
import pairmatching.domain.Course;
import pairmatching.domain.CrewShuffler;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pairs;
import pairmatching.repository.BackendCrewRepository;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.FrontendCrewRepository;
import pairmatching.repository.MissionRepository;
import pairmatching.service.PairMatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;
import pairmatching.view.PairMatchingInputParser;

public class AppConfig {

    private static final String BACKEND_CREW_MD = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_CREW_MD = "src/main/resources/frontend-crew.md";

    public static List<SubController> subControllers() {
        MissionRepository missionRepository = missionRepository();
        return Arrays.asList(pairMatchingController(missionRepository), parFindController(missionRepository),
                getInitializeController(missionRepository));
    }

    public static MissionRepository missionRepository() {
        MissionRepository missionRepository = new MissionRepository();
        addMissionByCourse(missionRepository, Course.BACKEND);
        addMissionByCourse(missionRepository, Course.FRONTEND);
        return missionRepository;
    }

    private static void addMissionByCourse(MissionRepository missionRepository, Course course) {
        Arrays.stream(Level.values()).forEach(level -> level.getMission()
                .forEach(mission -> missionRepository.add(new Mission(course, level, mission), new Pairs())));
    }

    private static PairMatchingController pairMatchingController(MissionRepository missionRepository) {
        return new PairMatchingController(pairMatchingInputView(), getOutputView(),
                pairMatchingService(missionRepository));
    }

    private static InputView pairMatchingInputView() {
        return new InputView(new PairMatchingInputParser());
    }

    private static OutputView getOutputView() {
        return new OutputView();
    }

    private static PairMatchingService pairMatchingService(MissionRepository missionRepository) {
        return new PairMatchingService(new CrewShuffler(), missionRepository);
    }

    private static PairFindController parFindController(MissionRepository missionRepository) {
        return new PairFindController(missionRepository, pairMatchingInputView(), getOutputView());
    }

    private static InitializeController getInitializeController(MissionRepository missionRepository) {
        return new InitializeController(missionRepository, getOutputView());
    }

    public static BackendCrewRepository backendCrewRepository() {
        BackendCrewRepository backendCrewRepository = new BackendCrewRepository();
        addCrews(backendCrewRepository, Course.BACKEND);
        return backendCrewRepository;
    }

    public static FrontendCrewRepository frontendCrewRepository() {
        FrontendCrewRepository frontendCrewRepository = new FrontendCrewRepository();
        addCrews(frontendCrewRepository, Course.FRONTEND);
        return frontendCrewRepository;
    }

    private static void addCrews(CrewRepository crewRepository, Course course) {
        BufferedReader bufferedReader = getBufferedReader(course);
        while (true) {
            if (addCrew(crewRepository, course, bufferedReader)) {
                break;
            }
        }
    }

    private static BufferedReader getBufferedReader(Course course) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(getCourse(course)));
        } catch (IOException e) {
            throw new IllegalArgumentException(NOT_FOUND_CREW_MD.getMessage());
        }
        return bufferedReader;
    }

    private static String getCourse(Course course) {
        if (course == Course.BACKEND) {
            return BACKEND_CREW_MD;
        }
        return FRONTEND_CREW_MD;
    }

    private static boolean addCrew(CrewRepository crewRepository, Course course, BufferedReader bufferedReader) {
        String crew = getString(bufferedReader);
        if (crew == null) {
            return true;
        }
        crewRepository.add(course, crew);
        return false;
    }

    private static String getString(BufferedReader bufferedReader) {
        String crew;
        try {
            crew = bufferedReader.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(NOT_FOUND_CREW_MD.getMessage());
        }
        if (crew == null) {
            return null;
        }
        return crew;
    }
}
