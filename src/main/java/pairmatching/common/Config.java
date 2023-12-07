package pairmatching.common;

import pairmatching.controller.MainController;
import pairmatching.domain.PairMatcher;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;
import pairmatching.view.SpacerPrinter;

public final class Config {

    private final PairMatcher pairMatcher;
    private final InputView inputView;
    private final OutputView outputView;

    private final MainController mainController;

    public Config() {
        this.pairMatcher = new PairMatcher();
        final SpacerPrinter spacerPrinter = new SpacerPrinter();
        this.inputView = new InputView(spacerPrinter);
        this.outputView = new OutputView(spacerPrinter);
        this.mainController = new MainController(inputView, outputView, pairMatcher);
    }

    public MainController mainController() {
        return this.mainController;
    }
}
