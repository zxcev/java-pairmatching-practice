package pairmatching.view;

public final class InputConverter {

    private static final String COMMA_WITH_WHITESPACE_DELIMETER = ", ";

    public static Tuple separateToTuple(final String input) {
        final String[] values = input
                .split(COMMA_WITH_WHITESPACE_DELIMETER);

        return new Tuple(
                values[0],
                values[1],
                values[2]
        );
    }

}
