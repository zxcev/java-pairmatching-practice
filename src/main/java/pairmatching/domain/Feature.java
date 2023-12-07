package pairmatching.domain;

import java.util.List;

public enum Feature {
    NOT_SELECTED("0"),
    MATCH("1"),
    QUERY("2"),
    INIT("3"),
    QUIT("Q");

    private final String code;

    private static final List<Feature> features = List.of(values());

    Feature(final String code) {
        this.code = code;
    }

    public static Feature from(final String input) {
        return features.stream()
                .filter(f -> f.code.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("기능은 반드시 `1`, `2`, `3`, `Q` 중 하나를 입력해야 합니다."));
    }
}
