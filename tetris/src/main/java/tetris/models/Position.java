package tetris.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode
@AllArgsConstructor
/**
 * Class representing a position in the game grid.
 */
public class Position {
    private int posX;
    private int posY;
}
