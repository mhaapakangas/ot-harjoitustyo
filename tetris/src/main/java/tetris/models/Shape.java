package tetris.models;

import lombok.Getter;

import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;

@Getter
public abstract class Shape {
    protected Position position;
    protected int orientation = 0;
    protected int[][][] orientations;

    public void moveLeft(int[][] grid) {
        Position newPosition = new Position(position.getPosX() - 1, position.getPosY());
        if (isNotColliding(grid, newPosition, orientations[orientation])) {
            position = newPosition;
        }
    }

    public void moveRight(int[][] grid) {
        Position newPosition = new Position(position.getPosX() + 1, position.getPosY());
        if (isNotColliding(grid, newPosition, orientations[orientation])) {
            position = newPosition;
        }
    }

    public void moveDown(int[][] grid) {
        Position newPosition = new Position(position.getPosX(), position.getPosY() + 1);
        if (isNotColliding(grid, newPosition, orientations[orientation])) {
            position = newPosition;
        }
    }

    public boolean canMoveDown(int[][] grid) {
        Position newPosition = new Position(position.getPosX(), position.getPosY() + 1);
        return isNotColliding(grid, newPosition, orientations[orientation]);
    }

    public void rotate(int[][] grid) {
        int newOrientation = (orientation + 1) % 4;
        if (isNotColliding(grid, position, orientations[newOrientation])) {
            orientation = newOrientation;
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
