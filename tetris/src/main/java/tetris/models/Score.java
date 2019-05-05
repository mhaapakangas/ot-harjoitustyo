package tetris.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Class representing a game score.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Score {
    private int score;
    private String username;
}
