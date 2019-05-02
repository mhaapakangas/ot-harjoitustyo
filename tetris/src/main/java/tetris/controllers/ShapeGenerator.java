package tetris.controllers;

import com.google.inject.Inject;
import tetris.models.Position;
import tetris.models.shapes.*;

import java.util.Random;

import static tetris.models.Constants.SHAPE_COUNT;

/**
 * This generates shapes for the game.
 */
public class ShapeGenerator {
    private Random random;

    @Inject
    public ShapeGenerator(Random random) {
        this.random = random;
    }

    /**
     * Generates a new random shape.
     * @return the shape.
     */
    public Shape getNewShape() {
        switch (random.nextInt(SHAPE_COUNT)) {
            case 0:
                return new TShape(new Position(4, 0));
            case 1:
                return new LShape(new Position(4, 0));
            case 2:
                return new SShape(new Position(4, 0));
            case 3:
                return new ZShape(new Position(4, 0));
            case 4:
                return new JShape(new Position(4, 0));
            case 5:
                return new IShape(new Position(3, -1));
            default:
                return new OShape(new Position(4, 0));
        }
    }
}
