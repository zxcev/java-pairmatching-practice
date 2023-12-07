package pairmatching.domain.crew;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

final class CrewsTest {
    void nextCrews_shouldReturnThreeCrewsWhenQueueSizeIsThree() {
        // Arrange
        List<String> crewNamesList = Arrays.asList("Crew1", "Crew2", "Crew3");
        Crews crews = Crews.of(crewNamesList);

        // Act
        List<Crew> result = crews.nextCrews();

        // Assert
        Assertions.assertThat(result).hasSize(3);
    }

    @Test
    void nextCrews_shouldReturnTwoCrewsWhenQueueSizeIsNotThree() {
        // Arrange
        List<String> crewNamesList = Arrays.asList("Crew1", "Crew2");
        Crews crews = Crews.of(crewNamesList);

        // Act
        List<Crew> result = crews.nextCrews();

        // Assert
        Assertions.assertThat(result).hasSize(2);
    }

    @Test
    void nextCrews_shouldReturnEmptyListWhenQueueIsEmpty() {
        // Arrange
        List<String> emptyList = Arrays.asList();
        Crews crews = Crews.of(emptyList);

        // Act
        // Assert
        Assertions.assertThatThrownBy(crews::nextCrews)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("더 이상 뽑을 크루가 존재하지 않습니다.");
    }

    @Test
    void isNotEmpty_shouldReturnTrueWhenQueueIsNotEmpty() {
        // Arrange
        List<String> crewNamesList = Arrays.asList("Crew1", "Crew2", "Crew3");
        Crews crews = Crews.of(crewNamesList);

        // Act
        boolean result = crews.isNotEmpty();

        // Assert
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void isNotEmpty_shouldReturnFalseWhenQueueIsEmpty() {
        // Arrange
        List<String> emptyList = Arrays.asList();
        Crews crews = Crews.of(emptyList);

        // Act
        boolean result = crews.isNotEmpty();

        // Assert
        Assertions.assertThat(result).isFalse();
    }
}