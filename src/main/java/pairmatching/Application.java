package pairmatching;

import pairmatching.common.Config;
import pairmatching.controller.MainController;

public final class Application {
    public static void main(final String[] args) {
        final MainController mainController = new Config().mainController();
        mainController.run();
    }
}
