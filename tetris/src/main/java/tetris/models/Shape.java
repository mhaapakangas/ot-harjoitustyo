package tetris.models;

import lombok.Getter;

import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;

public abstract class Shape {
    @Getter
    protected Position position;
    private int rotationIndex = 0;
    protected int[][][] rotations;

    public int[][] getOrientation() {
        return rotations[rotationIndex];
    }

    public void moveLeft(int[][] grid) {
        Position newPosition = new Position(position.getPosX() - 1, position.getPosY());
        if (isNotColliding(grid, newPosition, getOrientation())) {
            position = newPosition;
        }
    }

    public void moveRight(int[][] grid) {
        Position newPosition = new Position(position.getPosX() + 1, position.getPosY());
        if (isNotColliding(grid, newPosition, getOrientation())) {
            position = newPosition;
        }
    }

    public void moveDown(int[][] grid) {
        Position newPosition = new Position(position.getPosX(), position.getPosY() + 1);
        if (isNotColliding(grid, newPosition, getOrientation())) {
            position = newPosition;
        }
    }

    public boolean canMoveDown(int[][] grid) {
        Position newPosition = new Position(position.getPosX(), position.getPosY() + 1);
        return isNotColliding(grid, newPosition, getOrientation());
    }

    public void rotate(int[][] grid) {
        int newRotationIndex = (rotationIndex + 1) % 4;
        if (isNotColliding(grid, position, rotations[newRotationIndex])) {
            rotationIndex = newRotationIndex;
        }
    }

    private boolean isNotColliding(int[][] grid, Position newPosition, int[][] newOrientation) {
        for (int i = 0; i < newOrientation.length; i++) {
            for (int j = 0; j < newOrientation[0].length; j++) {
                if (newOrientation[j][i] != 0) {
                    if (newPosition.getPosX() + i < 0) {
                        return false;
                    }
                    if (newPosition.getPosX() + i >= GRID_WIDTH) {
                        return false;
                    }
                    if (newPosition.getPosY() + j >= GRID_HEIGHT) {
                        return false;
                    }
                    if (grid[newPosition.getPosX() + i][newPosition.getPosY() + j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
