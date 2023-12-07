package pairmatching.domain.crew;

import java.util.List;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private static final List<Course> courses = List.of(values());
    private final String name;

    Course(final String name) {
        this.name = name;
    }

    public static Course from(final String input) {
        return courses.stream()
                .filter(c -> c.name.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("프론트엔드, 백엔드만 선택 가능합니다."));
    }

    public String getName() {
        return name;
    }
}
