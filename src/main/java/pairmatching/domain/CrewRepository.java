package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.domain.crew.Course;
import pairmatching.domain.crew.Crew;
import pairmatching.domain.crew.Crews;
import pairmatching.util.CrewReader;

public final class CrewRepository {

    private static final Map<Course, List<Crew>> CREW_MAP_BY_COURSE = new HashMap<>();

    static {
        List.of(Course.FRONTEND, Course.BACKEND)
                .forEach((course) -> {
                    final List<Crew> crews = CrewReader.readCrewByCourse(course);
                    CREW_MAP_BY_COURSE.put(course, crews);
                });
    }

    public static Crews findShuffledCrewsByCourse(final Course course) {
        final List<String> crewNames = findCrewNamesByCourse(course);
        final List<String> shuffledCrewNames = Randoms.shuffle(crewNames);

        return Crews.of(shuffledCrewNames);
    }

    private static List<String> findCrewNamesByCourse(final Course course) {
        return CREW_MAP_BY_COURSE.get(course)
                .stream()
                .map(Crew::getName)
                .toList();
    }

}
