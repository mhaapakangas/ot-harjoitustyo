package tetris.models.shapes;

import tetris.models.BlockColor;
import tetris.models.Position;

/**
 * Class representing J-shape, 3 blocks in a line with one added above the left side.
 */
public class JShape extends Shape {
    public JShape(Position position) {
        this.position = position;
        this.color = BlockColor.ORANGE;

        this.rotations = new int[4][][];
        this.rotations[0] = new int[][]{
            {1, 0, 0},
            {1, 1, 1},
            {0, 0, 0}
        };
        this.rotations[1] = new int[][]{
            {0, 1, 1},
            {0, 1, 0},
            {0, 1, 0}
        };
        this.rotations[2] = new int[][]{
            {0, 0, 0},
            {1, 1, 1},
            {0, 0, 1}
        };
        this.rotations[3] = new int[][]{
            {0, 1, 0},
            {0, 1, 0},
            {1, 1, 0}
        };
    }
}
