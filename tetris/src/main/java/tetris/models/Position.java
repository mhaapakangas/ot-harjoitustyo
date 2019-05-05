package tetris.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


/**
 * Class representing a position in the game grid.
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Position {
    private int posX;
    private int posY;
}
