package pairmatching.view;

import java.util.regex.Pattern;

public final class InputValidator {
    private static final String SINGLE_COMMAS_SEPARATED_REGEX = "^[1-9가-힣]+$";
    private static final String MULTIPLE_COMMAS_SEPARATED_REGEX = "^([1-9가-힣]+)+(, [1-9가-힣]+)*$";

    public static void validateRegexInput(final String input) {
        ValidationChain.withArgument(input)
                .willThrow(new IllegalArgumentException("유효하지 않은 입력입니다."))
                .validate(InputValidator::_validateRegexInput);
    }

    public static void _validateRegexInput(final String input) {
        if (!Pattern.matches(SINGLE_COMMAS_SEPARATED_REGEX, input) &&
                !Pattern.matches(MULTIPLE_COMMAS_SEPARATED_REGEX, input)) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateNumericInput(final String input) {
        ValidationChain.withArgument(input)
                .willThrow(new IllegalArgumentException())
                .validate(InputValidator::_validateNullOrBlankInput)
                .validate(InputValidator::_validateNumericInput);
    }

    public static void _validateNumericInput(final String input) {
        final boolean startsWithZero = input.charAt(0) == '0';
        final boolean isNotAllDigit = !input.chars()
                .allMatch(Character::isDigit);

        if (startsWithZero && isNotAllDigit) {
            throw new IllegalArgumentException();
        }
    }

    private static void _validateNullOrBlankInput(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

}
