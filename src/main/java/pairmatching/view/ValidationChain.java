package pairmatching.view;

import java.util.function.Consumer;

public class ValidationChain<T> {

    // validate chain
    // ValidationChain.with(argument)
    // ValidationChain.willThrow(exception)
    //   .validate(exception1)
    //   .validate(exception2)
    //   .validate(exception3)

    // -> ValidationChained.validate(Validate::validateXXX1);
    // -> ValidationChained.validate(Validate::validateXXX2);
    // -> ValidationChained.validate(Validate::validateXXX3);

    private final T argument;

    public ValidationChain(final T argument) {
        this.argument = argument;
    }

    public static <T> ValidationChain<T> withArgument(final T argument) {
        return new ValidationChain<>(argument);
    }

    public <E extends RuntimeException> ValidationChainWithException<T, E> willThrow(
            final E exception) {
        return new ValidationChainWithException<>(argument, exception);
    }

    public static final class ValidationChainWithException<T, E extends RuntimeException> {
        private final T argument;
        private final E exception;

        public ValidationChainWithException(
                final T argument,
                final E exception
        ) {
            this.argument = argument;
            this.exception = exception;
        }

        public ValidationChainWithException<T, E> validate(final Consumer<T> consumer) {
            try {
                consumer.accept(argument);
                return new ValidationChainWithException<>(argument, exception);
            } catch (final RuntimeException ignoredException) {
                throw exception;
            }
        }
    }


}
