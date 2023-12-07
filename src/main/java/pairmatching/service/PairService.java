package pairmatching.service;

import java.util.List;
import pairmatching.controller.dto.MatchRequestDto;
import pairmatching.controller.dto.PairDto;
import pairmatching.controller.dto.PairsDto;
import pairmatching.domain.PairMatcher;
import pairmatching.domain.PairRepository;
import pairmatching.domain.crew.Pair;

public final class PairService {

    private static final int MAX_TRY_COUNT = 3;

    private final PairMatcher pairMatcher;

    public PairService(final PairMatcher pairMatcher) {
        this.pairMatcher = pairMatcher;
    }

    public void match(final MatchRequestDto dto) {
        int tryCount = 0;

        while (tryCount++ < MAX_TRY_COUNT) {
            final boolean isOk = tryMatch(dto);

            if (isOk) {
                return;
            }
        }
        throw new IllegalStateException("페어 매칭에 3회 실패하여 어플리케이션이 종료됩니다.");
    }

    private boolean tryMatch(final MatchRequestDto dto) {
        final List<Pair> matchedPairs = pairMatcher.match(dto);
        final boolean anyMatchedInSameLevel = PairRepository.anyMatchedInSameLevel(matchedPairs, dto.course(),
                dto.level());
        if (anyMatchedInSameLevel) {
            return false;
        }

        PairRepository.savePairsByCourse(dto.course(), matchedPairs);

        return true;
    }

    public PairsDto getPairsByCondition(final MatchRequestDto dto) {
        final List<PairDto> pairs = PairRepository.findPairsByCondition(dto).stream()
                .map(Pair::toPairDto)
                .toList();

        if (pairs.isEmpty()) {
            throw new IllegalStateException("조건에 일치하는 페어가 존재하지 않습니다.");
        }
        return new PairsDto(pairs);
    }


    public void clearMatchedInfo(final MatchRequestDto dto) {
        PairRepository.removeByCondition(dto.course(), dto.mission());
    }

    public void clearAllMatchedInfo() {
        PairRepository.clear();
    }

    public PairsDto findPairsByCondition(final MatchRequestDto dto) {
        final List<PairDto> pairs = PairRepository.findPairsByCondition(dto).stream()
                .map(Pair::toPairDto)
                .toList();

        return new PairsDto(pairs);
    }

    public boolean checkMatchedDataExistence(final MatchRequestDto dto) {
        final PairsDto result = findPairsByCondition(dto);
        return !result.pairs().isEmpty();
    }

}
