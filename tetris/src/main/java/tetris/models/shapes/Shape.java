package tetris.models.shapes;

import lombok.Getter;
import tetris.models.BlockColor;
import tetris.models.Position;

import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;

public abstract class Shape {
    @Getter
    protected Position position;
    @Getter
    protected BlockColor color;
    private int rotationIndex = 0;
    protected int[][][] rotations;

    public int[][] getOrientation() {
        return rotations[rotationIndex];
    }

    public void moveLeft(int[][] grid) {
        Position newPosition = new Position(position.getPosX() - 1, position.getPosY());
        if (!isColliding(grid, newPosition, getOrientation())) {
            position = newPosition;
        }
    }

    public void moveRight(int[][] grid) {
        Position newPosition = new Position(position.getPosX() + 1, position.getPosY());
        if (!isColliding(grid, newPosition, getOrientation())) {
            position = newPosition;
        }
    }

    public void moveDown(int[][] grid) {
        Position newPosition = new Position(position.getPosX(), position.getPosY() + 1);
        if (!isColliding(grid, newPosition, getOrientation())) {
            position = newPosition;
        }
    }

    public boolean canMoveDown(int[][] grid) {
        Position newPosition = new Position(position.getPosX(), position.getPosY() + 1);
        return !isColliding(grid, newPosition, getOrientation());
    }

    public boolean isColliding(int[][] grid) {
        return isColliding(grid, position, getOrientation());
    }

    public void rotate(int[][] grid) {
        int newRotationIndex = (rotationIndex + 1) % 4;
        if (!isColliding(grid, position, rotations[newRotationIndex])) {
            rotationIndex = newRotationIndex;
        }
    }

    private boolean isColliding(int[][] grid, Position position, int[][] orientation) {
        for (int i = 0; i < orientation.length; i++) {
            for (int j = 0; j < orientation[0].length; j++) {
                if (orientation[j][i] != 0) {
                    if (isOutsideScreen(position, i, j)) {
                        return true;
                    }

                    if (isCollidingWithGrid(grid, position, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isOutsideScreen(Position position, int x, int y) {
        if (position.getPosX() + x < 0) {
            return true;
        }
        if (position.getPosX() + x >= GRID_WIDTH) {
            return true;
        }

        if (position.getPosY() + y < 0) {
            return true;
        }
        return position.getPosY() + y >= GRID_HEIGHT;
    }

    private boolean isCollidingWithGrid(int[][] grid, Position position, int x, int y) {
        return grid[position.getPosX() + x][position.getPosY() + y] != 0;
    }
}
