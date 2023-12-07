package pairmatching.controller.dto;

import pairmatching.domain.crew.Course;
import pairmatching.domain.crew.Level;
import pairmatching.domain.crew.Mission;
import pairmatching.view.Tuple;

public record MatchRequestDto(
        Course course,
        Level level,
        Mission mission
) {


    public static MatchRequestDto from(final Tuple tuple) {
        final Course course = Course.from(tuple.a());
        final Level level = Level.from(tuple.b());
        final Mission mission = Mission.from(tuple.c());

        return new MatchRequestDto(
                course,
                level,
                mission
        );
    }
}
