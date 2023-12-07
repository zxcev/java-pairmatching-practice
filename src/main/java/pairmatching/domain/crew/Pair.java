package pairmatching.domain.crew;

import java.util.List;
import pairmatching.controller.dto.PairDto;

public final class Pair {
    private final List<Crew> crews;

    private final Level level;

    private final Course course;

    private final Mission mission;

    public Pair(
            final List<Crew> crews,
            final Level level,
            final Course course,
            final Mission mission
    ) {
        this.crews = crews;
        this.level = level;
        this.course = course;
        this.mission = mission;
    }

    public boolean isMember(final Crew crew) {
        return crews.contains(crew);
    }

    public List<Crew> getCrews() {
        return crews;
    }

    public Level getLevel() {
        return level;
    }

    public Course getCourse() {
        return course;
    }

    public Mission getMission() {
        return mission;
    }

    public PairDto toPairDto() {
        String thirdCrewName = null;
        if (crews.size() == 3) {
            thirdCrewName = crews.get(2).getName();
        }

        return new PairDto(
                crews.get(0).getName(),
                crews.get(1).getName(),
                thirdCrewName
        );
    }

    public boolean containsAnyOf(final List<Crew> otherCrews) {
        return crews.stream()
                .anyMatch(otherCrews::contains);
    }
}
