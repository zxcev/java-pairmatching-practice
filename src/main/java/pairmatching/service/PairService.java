package pairmatching.service;

import java.util.List;
import pairmatching.controller.dto.MatchRequestDto;
import pairmatching.controller.dto.PairDto;
import pairmatching.controller.dto.PairsDto;
import pairmatching.domain.PairMatcher;
import pairmatching.domain.PairRepository;
import pairmatching.domain.crew.Pair;

public final class PairService {

    private final PairMatcher pairMatcher;

    public PairService(final PairMatcher pairMatcher) {
        this.pairMatcher = pairMatcher;
    }

    public void match(final MatchRequestDto dto) {
        pairMatcher.match(dto);
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
