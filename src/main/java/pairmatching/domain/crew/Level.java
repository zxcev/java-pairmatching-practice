package pairmatching.domain.crew;

import java.util.List;

public enum Level {
    LEVEL_1("레벨1"),
    LEVEL_2("레벨2"),
    LEVEL_3("레벨3"),
    LEVEL_4("레벨4"),
    LEVEL_5("레벨5");

    private final String value;

    private static final List<Level> levels = List.of(values());

    Level(final String value) {
        this.value = value;
    }

    public static Level from(final String input) {
        return levels.stream()
                .filter(c -> c.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("레벨1~5만 선택 가능합니다."));
    }
}
