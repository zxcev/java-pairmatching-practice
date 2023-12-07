package pairmatching.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import pairmatching.domain.crew.Course;
import pairmatching.domain.crew.Crew;

public final class CrewReader {
    private static final String BASE_PATH = "./src/main/resources";
    private static final Map<Course, String> FILENAME_MAP = Map.of(
            Course.FRONTEND, "frontend-crew",
            Course.BACKEND, "backend-crew"
    );

    public static List<Crew> readCrewByCourse(final Course course) {

        try {
            final Path filePath = resolveFilePath(course);
            final List<String> crewNames = Files.readAllLines(filePath);

            return crewNames.stream()
                    .map(Crew::new)
                    .toList();

        } catch (IOException e) {
            throw new IllegalStateException("파일을 읽는데 실패 하였습니다.");
        }
    }

    private static Path resolveFilePath(final Course course) {
        final String filePath = String.format(
                "%s/%s.md",
                BASE_PATH,
                FILENAME_MAP.get(course)
        );
        return Paths.get(filePath);
    }
}
