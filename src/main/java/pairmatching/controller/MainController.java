package pairmatching.controller;

import pairmatching.controller.dto.MatchRequestDto;
import pairmatching.controller.dto.PairsDto;
import pairmatching.domain.Feature;
import pairmatching.domain.PairMatcher;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public final class MainController {

    private final InputView inputView;
    private final OutputView outputView;

    private final PairMatcher pairMatcher;

    public MainController(
            final InputView inputView,
            final OutputView outputView,
            final PairMatcher pairMatcher
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairMatcher = pairMatcher;
    }

    public void run() {
        Feature feature = Feature.NOT_SELECTED;

        while (feature != Feature.QUIT) {
            feature = inputView.inputFeature();
            processFeature(feature);
        }
    }

    private void processFeature(final Feature feature) {
        if (feature == Feature.MATCH) {
            match();
        }
        if (feature == Feature.QUERY) {
            query();
        }
        if (feature == Feature.INIT) {
            init();
        }
    }


    private void match() {
        final MatchRequestDto dto = inputView.inputMatchRequestDto();
        doMatching(dto);
        final PairsDto pairs = pairMatcher.findPairsByCondition(dto);
        outputView.printPairs(pairs);
    }

    private void doMatching(final MatchRequestDto dto) {
        if (pairMatcher.hasMatchedData(dto)) {
            final boolean refusedRematching = !inputView.inputRematch();

            if (refusedRematching) {
                return;
            }
            pairMatcher.clearMatchedInfo(dto);
        }

        pairMatcher.match(dto);
    }

    private void query() {
        final MatchRequestDto dto = inputView.inputMatchRequestDto();
        final PairsDto pairs = pairMatcher.getPairsByCondition(dto);
        outputView.printPairs(pairs);
    }

    private void init() {
        pairMatcher.clearAllMatchedInfo();
        outputView.printInit();
    }

}
