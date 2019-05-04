package tetris.models.shapes;

import tetris.models.BlockColor;
import tetris.models.Position;

/**
 * Class representing O-shape, square of 2 x 2 blocks.
 */
public class OShape extends Shape {
    public OShape(Position position) {
        this.position = position;
        this.color = BlockColor.PINK;

        this.rotations = new int[4][][];
        this.rotations[0] = new int[][]{
            {1, 1},
            {1, 1}
        };
        this.rotations[1] = this.rotations[0];
        this.rotations[2] = this.rotations[0];
        this.rotations[3] = this.rotations[0];
    }
}
