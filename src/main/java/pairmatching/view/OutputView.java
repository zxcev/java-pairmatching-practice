package pairmatching.view;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import pairmatching.controller.dto.PairDto;
import pairmatching.controller.dto.PairsDto;

public final class OutputView {
    private static final String INIT_MSG = "초기화 되었습니다.";

    private final SpacerPrinter printer;

    public OutputView(final SpacerPrinter printer) {
        this.printer = printer;
    }


    public void printPairs(final PairsDto pairs) {
        final String renderedString = pairs.pairs().stream()
                .map(this::renderPair)
                .collect(Collectors.joining("\n"));

        printer.print("페어 매칭 결과입니다.");
        printer.print(renderedString);
    }

    private String renderPair(final PairDto pair) {
        return Stream.of(pair.firstCrewName(), pair.secondCrewName(), pair.thirdCrewName())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" : "));
    }

    public void printInit() {
        printer.print(INIT_MSG);
    }
}
