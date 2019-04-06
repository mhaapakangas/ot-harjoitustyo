package tetris.controllers;

import lombok.Getter;
import tetris.models.Position;
import tetris.models.Shape;
import tetris.models.TShape;

import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;

public class GameService {
    private Shape currentShape;
    @Getter
    private int[][] grid = new int[GRID_WIDTH][GRID_HEIGHT];
    private long lastUpdate;

    public GameService() {
        currentShape = new TShape(new Position(5, 0));
        lastUpdate = System.currentTimeMillis();
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > 250) {
            currentShape.moveDown(grid);
            lastUpdate = currentTime;
            if (!currentShape.canMoveDown(grid)) {
                int[][] shapeOrientation = currentShape.getOrientations()[currentShape.getOrientation()];
                Position shapePosition = currentShape.getPosition();
                for (int i = 0; i < shapeOrientation.length; i++) {
                    for (int j = 0; j < shapeOrientation[0].length; j++) {
                        if (shapeOrientation[j][i] != 0) {
                            grid[shapePosition.getPosX() + i][shapePosition.getPosY() + j] = 1;
                        }
                    }
                }
                currentShape = new TShape(new Position(5, 0));
            }
        }
    }

    public void moveShapeLeft() {
        currentShape.moveLeft(grid);
    }

    public void moveShapeRight() {
        currentShape.moveRight(grid);
    }

    public void rotateShape() {
        currentShape.rotate(grid);
    }

    public Position getShapePosition() {
        return currentShape.getPosition();
    }

    public int[][] getShapeOrientation() {
        return currentShape.getOrientations()[currentShape.getOrientation()];
    }
}
