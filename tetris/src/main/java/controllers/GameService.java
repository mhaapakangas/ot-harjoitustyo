package controllers;

import lombok.Getter;
import models.OShape;
import models.Position;
import models.Shape;

import static models.Constants.GRID_HEIGHT;
import static models.Constants.GRID_WIDTH;

public class GameService {
    private Shape currentShape;
    @Getter
    private int[][] grid = new int[GRID_WIDTH][GRID_HEIGHT];
    private long lastUpdate;

    public GameService() {
        currentShape = new OShape(new Position(150, 0));
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
                            grid[shapePosition.getGridPosX() + i][shapePosition.getGridPosY() + j] = 1;
                        }
                    }
                }
                currentShape = new OShape(new Position(150, 0));
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
