package pairmatching.domain.crew;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Mission {
    CAR_RACING("자동차경주", Level.LEVEL_1),
    LOTTO("로또", Level.LEVEL_1),
    NUMBER_BASEBALL("숫자야구게임", Level.LEVEL_1),
    SHOPPING_CART("장바구니", Level.LEVEL_2),
    PAYMENT("결제", Level.LEVEL_2),
    SUBWAY_MAP("지하철노선도", Level.LEVEL_2),
    PERFORMANCE_IMPROVEMENT("성능개선", Level.LEVEL_4),
    DEPLOYMENT("배포", Level.LEVEL_4);

    private static final Map<String, Mission> missionsMap = new HashMap<>();

    static {
        for (final Mission mission : values()) {
            missionsMap.put(mission.title, mission);
        }
    }

    private final String title;
    private final Level level;

    Mission(
            final String title,
            final Level level
    ) {
        this.title = title;
        this.level = level;
    }

    public static Mission from(final String input) {
        return Optional.ofNullable(missionsMap.get(input))
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("그런 미션이 없습니다."));
    }

    public String getTitle() {
        return title;
    }

    public Level getLevel() {
        return level;
    }
}
