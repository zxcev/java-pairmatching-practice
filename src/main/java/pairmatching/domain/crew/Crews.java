package pairmatching.domain.crew;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class Crews {
    private final Queue<String> crewNames;

    private Crews(final Queue<String> crewNames) {
        this.crewNames = crewNames;
    }

    public static Crews of(final List<String> crewNames) {
        final Queue<String> crewNamesQueue = new LinkedList<>(crewNames);
        return new Crews(crewNamesQueue);
    }

    public List<Crew> nextCrews() {
        if (crewNames.size() == 3) {
            return pickMany(3);
        }
        return pickMany(2);
    }

    private List<Crew> pickMany(int count) {
        final List<Crew> crews = new ArrayList<>();

        while (count-- > 0) {
            crews.add(pick());
        }

        return crews;
    }

    private Crew pick() {
        if (crewNames.isEmpty()) {
            throw new IllegalArgumentException("더 이상 뽑을 크루가 존재하지 않습니다.");
        }
        return new Crew(crewNames.poll());
    }

    public boolean isNotEmpty() {
        return !crewNames.isEmpty();
    }
}
