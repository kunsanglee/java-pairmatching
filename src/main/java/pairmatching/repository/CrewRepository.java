package pairmatching.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pairmatching.domain.Mission;

public class CrewRepository {
    private static final List<String> backendCrews = new ArrayList<>();
    private static final List<String> frontendCrews = new ArrayList<>();

    static {
        try {
            addCrews(backendCrews, "src/main/resources/backend-crew.md");
            addCrews(frontendCrews, "src/main/resources/frontend-crew.md");
        } catch (IOException e) {
            throw new IllegalArgumentException("읽기에 실패했습니다.");
        }
    }

    private static void addCrews(List<String> targetCrews, String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            targetCrews.add(line);
        }
        bufferedReader.close();
    }

    public static List<String> getCrews(Mission mission) {
        if (mission.isFrontEnd()) {
            return Collections.unmodifiableList(frontendCrews);
        }
        return Collections.unmodifiableList(backendCrews);
    }
}
