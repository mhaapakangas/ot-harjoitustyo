package controllers;

import lombok.Getter;
import models.Position;
import models.Shape;

import static models.Constants.GRID_HEIGHT;
import static models.Constants.GRID_WIDTH;

public class GameService {
    private Shape currentShape;
    @Getter
    private boolean[][] grid = new boolean[GRID_WIDTH][GRID_HEIGHT];
    private long lastUpdate;

    public GameService() {
        currentShape = new Shape(new Position(150, 0));
        lastUpdate = System.currentTimeMillis();
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > 250) {
            currentShape.moveDown(grid);
            lastUpdate = currentTime;
            if (!currentShape.canFall(grid)) {
                grid[currentShape.getPosition().getGridPosX()][currentShape.getPosition().getGridPosY()] = true;
                currentShape = new Shape(new Position(150, 0));
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
}
