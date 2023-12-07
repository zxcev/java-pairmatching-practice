package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.controller.dto.MatchRequestDto;
import pairmatching.domain.Feature;

public final class InputView {

    private final SpacerPrinter printer;

    private boolean isFirstPrint = false;

    public InputView(final SpacerPrinter printer) {
        this.printer = printer;
    }

    public Feature inputFeature() {
        return RetryHandler.handleRetry(() -> {
            printer.print(Message.FEATURE_INPUT_MSG);
            return Feature.from(readLine());
        });
    }

    public boolean inputRematch() {
        return RetryHandler.handleRetry(() -> {
            printer.print(Message.REMATCH_INPUT_MSG);
            final String input = readLine();
            if ("네".equals(input)) {
                return true;
            }
            if ("아니오".equals(input)) {
                return false;
            }
            throw new IllegalArgumentException("반드시 네 | 아니오 중 하나로 답해주세요.");
        });
    }

    public MatchRequestDto inputMatchRequestDto() {
        return RetryHandler.handleRetry(() -> {
            if (isFirstPrint) {
                isFirstPrint = false;
                System.out.println(Message.SELECTION_INPUT_PREVIEW_MSG);
            }
            printer.print(Message.SELECTION_INPUT_MSG);
            final String input = readLine();
            InputValidator.validateRegexInput(input);
            final Tuple tuple = InputConverter.separateToTuple(input);

            return MatchRequestDto.from(tuple);
        });
    }

    private static String readLine() {
        return Console.readLine().trim();
    }
}
