package pairmatching.controller;

import pairmatching.controller.dto.MatchRequestDto;
import pairmatching.controller.dto.PairsDto;
import pairmatching.domain.Feature;
import pairmatching.service.PairService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public final class MainController {

    private final InputView inputView;
    private final OutputView outputView;
    private final PairService pairService;

    public MainController(
            final InputView inputView,
            final OutputView outputView,
            final PairService pairService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairService = pairService;
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
        final PairsDto pairs = pairService.findPairsByCondition(dto);
        outputView.printPairs(pairs);
    }

    private void doMatching(final MatchRequestDto dto) {
        if (pairService.checkMatchedDataExistence(dto)) {
            final boolean refusedRematching = !inputView.inputRematch();

            if (refusedRematching) {
                return;
            }
            pairService.clearMatchedInfo(dto);
        }

        pairService.match(dto);
    }

    private void query() {
        final MatchRequestDto dto = inputView.inputMatchRequestDto();
        final PairsDto pairs = pairService.getPairsByCondition(dto);
        outputView.printPairs(pairs);
    }

    private void init() {
        pairService.clearAllMatchedInfo();
        outputView.printInit();
    }

}
