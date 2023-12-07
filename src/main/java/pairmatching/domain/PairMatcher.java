package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import pairmatching.controller.dto.MatchRequestDto;
import pairmatching.domain.crew.Crew;
import pairmatching.domain.crew.Crews;
import pairmatching.domain.crew.Pair;

public final class PairMatcher {

    public List<Pair> match(final MatchRequestDto dto) {

        final Crews crews = CrewRepository.findShuffledCrewsByCourse(dto.course());
        final List<Pair> pairs = new ArrayList<>();

        matchRecursive(crews, pairs, dto);

        return pairs;
    }

    private void matchRecursive(
            final Crews crews,
            final List<Pair> pairs,
            final MatchRequestDto dto
    ) {
        final Pair pair = toPair(crews.nextCrews(), dto);
        pairs.add(pair);

        if (crews.isNotEmpty()) {
            matchRecursive(crews, pairs, dto);
        }
    }

    private Pair toPair(
            final List<Crew> crews,
            final MatchRequestDto dto
    ) {
        return new Pair(
                crews,
                dto.level(),
                dto.course(),
                dto.mission()
        );
    }

}
