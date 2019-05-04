package tetris.models.shapes;

import tetris.models.BlockColor;
import tetris.models.Position;

/**
 * Class representing Z-shape, 2 stacked lines of 2 blocks, with the one above offset to the left.
 */
public class ZShape extends Shape {
    public ZShape(Position position) {
        this.position = position;
        this.color = BlockColor.PURPLE;

        this.rotations = new int[4][][];
        this.rotations[0] = new int[][]{
            {1, 1, 0},
            {0, 1, 1},
            {0, 0, 0}
        };
        this.rotations[1] = new int[][]{
            {0, 0, 1},
            {0, 1, 1},
            {0, 1, 0}
        };
        this.rotations[2] = new int[][]{
            {0, 0, 0},
            {1, 1, 0},
            {0, 1, 1}
        };
        this.rotations[3] = new int[][]{
            {0, 1, 0},
            {1, 1, 0},
            {1, 0, 0}
        };
    }
}
