package tetris.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
/**
 * Class representing a game score.
 */
public class Score {
    private int score;
    private String username;
}
