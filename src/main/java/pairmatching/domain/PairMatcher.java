package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import pairmatching.controller.dto.MatchRequestDto;
import pairmatching.domain.crew.Crew;
import pairmatching.domain.crew.Crews;
import pairmatching.domain.crew.Pair;

public final class PairMatcher {

    private static final int MAX_TRY_COUNT = 3;

    public void match(final MatchRequestDto dto) {
        _match(dto, 0);
    }

    public void _match(
            final MatchRequestDto dto,
            final int tryCount
    ) {
        if (tryCount == MAX_TRY_COUNT) {
            throw new IllegalStateException("페어 매칭에 3회 실패하여 어플리케이션이 종료됩니다.");
        }

        final Crews crews = CrewRepository.findShuffledCrewsByCourse(dto.course());
        final List<Pair> pairs = new ArrayList<>();

        try {
            matchRecursive(crews, pairs, dto);
        } catch (final IllegalStateException e) {
            match(dto);
            return;
        }
        PairRepository.saveAllByCourse(dto.course(), pairs);
    }

    private List<Pair> matchRecursive(
            final Crews crews,
            final List<Pair> pairs,
            final MatchRequestDto dto
    ) {
        final Pair pair = toPair(crews.nextCrews(), dto);

        if (PairRepository.isMatchedInSameLevel(dto.course(), dto.level(), pair)) {
            throw new IllegalStateException("이미 매칭된 적이 있는 크루가 포함되어 있습니다.");
        }
        pairs.add(pair);

        if (crews.isNotEmpty()) {
            return matchRecursive(crews, pairs, dto);
        }

        return pairs;
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
