package models;

import lombok.Getter;

import static models.Constants.BLOCK_SIZE;
import static models.Constants.GRID_HEIGHT;
import static models.Constants.GRID_WIDTH;

@Getter
public abstract class Shape {
    protected Position position;
    protected int[][] orientation;

    public void moveLeft(int[][] grid) {
        Position newPosition = new Position(position.getPosX() - BLOCK_SIZE, position.getPosY());
        if (isNotColliding(grid, newPosition, orientation)) {
            position = newPosition;
        }
    }

    public void moveRight(int[][] grid) {
        Position newPosition = new Position(position.getPosX() + BLOCK_SIZE, position.getPosY());
        if (isNotColliding(grid, newPosition, orientation)) {
            position = newPosition;
        }
    }

    public void moveDown(int[][] grid) {
        Position newPosition = new Position(position.getPosX(), position.getPosY() + BLOCK_SIZE);
        if (isNotColliding(grid, newPosition, orientation)) {
            position = newPosition;
        }
    }

    public boolean canMoveDown(int[][] grid) {
        Position newPosition = new Position(position.getPosX(), position.getPosY() + BLOCK_SIZE);
        return isNotColliding(grid, newPosition, orientation);
    }

    private boolean isNotColliding(int[][] grid, Position newPosition, int[][] newOrientation) {
        for (int i = 0; i < newOrientation.length; i++) {
            for (int j = 0; j < newOrientation[0].length; j++) {
                if (newOrientation[i][j] != 0) {
                    if (newPosition.getGridPosX() + i < 0) {
                        return false;
                    }
                    if (newPosition.getGridPosX() + i >= GRID_WIDTH) {
                        return false;
                    }
                    if (newPosition.getGridPosY() + j >= GRID_HEIGHT) {
                        return false;
                    }
                    if (grid[newPosition.getGridPosX() + i][newPosition.getGridPosY() + j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
