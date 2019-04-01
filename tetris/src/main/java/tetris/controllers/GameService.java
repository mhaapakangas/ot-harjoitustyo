package tetris.controllers;

import lombok.Getter;
import tetris.models.OShape;
import tetris.models.Position;
import tetris.models.Shape;

import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;

public class GameService {
    private Shape currentShape;
    @Getter
    private int[][] grid = new int[GRID_WIDTH][GRID_HEIGHT];
    private long lastUpdate;

    public GameService() {
        currentShape = new OShape(new Position(5, 0));
        lastUpdate = System.currentTimeMillis();
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > 250) {
            currentShape.moveDown(grid);
            lastUpdate = currentTime;
            if (!currentShape.canMoveDown(grid)) {
                int[][] shapeOrientation = currentShape.getOrientation();
                Position shapePosition = currentShape.getPosition();
                for (int i = 0; i < shapeOrientation.length; i++) {
                    for (int j = 0; j < shapeOrientation[0].length; j++) {
                        if (shapeOrientation[i][j] != 0) {
                            grid[shapePosition.getPosX() + i][shapePosition.getPosY() + j] = 1;
                        }
                    }
                }
                currentShape = new OShape(new Position(5, 0));
            }
        }
    }

    public void moveShapeLeft() {
        currentShape.moveLeft(grid);
    }

    public void moveShapeRight() {
        currentShape.moveRight(grid);
    }

    public Position getShapePosition() {
        return currentShape.getPosition();
    }

    public int[][] getShapeOrientation() {
        return currentShape.getOrientation();
    }
}
