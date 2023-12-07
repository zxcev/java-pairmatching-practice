package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import pairmatching.controller.dto.MatchRequestDto;
import pairmatching.domain.crew.Course;
import pairmatching.domain.crew.Level;
import pairmatching.domain.crew.Mission;
import pairmatching.domain.crew.Pair;

public final class PairRepository {

    private static final Map<Course, List<Pair>> PAIR_MAP_BY_COURSE = Map.of(
            Course.FRONTEND, new ArrayList<>(),
            Course.BACKEND, new ArrayList<>()
    );

    public static List<Pair> findPairsByCourse(final Course course) {
        return Collections.unmodifiableList(
                PAIR_MAP_BY_COURSE.get(course)
        );
    }

    public static List<Pair> findPairsByCondition(final MatchRequestDto dto) {
        final List<Pair> pairs = findPairsByCourse(dto.course());

        return pairs.stream()
                .filter(p -> p.getLevel() == dto.level())
                .filter(p -> p.getMission() == dto.mission())
                .toList();
    }

    public static boolean anyMatchedInSameLevel(
            final List<Pair> pairs,
            final Course course,
            final Level level
    ) {
        final List<Pair> allPairs = findPairsByCourse(course);

        return allPairs.stream()
                .filter(p -> p.getLevel() == level)
                .anyMatch(p -> p.containsSameCrew(pairs));
    }

    public static void savePairsByCourse(
            final Course course,
            final List<Pair> pairs
    ) {
        PAIR_MAP_BY_COURSE.get(course)
                .addAll(pairs);
    }

    public static void removeByCondition(
            final Course course,
            final Mission mission
    ) {
        final List<Pair> pairs = PAIR_MAP_BY_COURSE.get(course);
        pairs.removeIf(p -> p.getMission() == mission);
    }

    public static void clear() {
        for (final List<Pair> pairs : PAIR_MAP_BY_COURSE.values()) {
            pairs.clear();
        }
    }
}
