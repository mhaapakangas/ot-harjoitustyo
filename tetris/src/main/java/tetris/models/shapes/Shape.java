package tetris.models.shapes;

import lombok.Getter;
import tetris.models.BlockColor;
import tetris.models.Position;

import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;

/**
 * Abstract class representing a game shape
 */
public abstract class Shape {
    @Getter
    protected Position position;
    @Getter
    protected BlockColor color;
    private int rotationIndex = 0;
    protected int[][][] rotations;

    /**
     * Returns current orientation of the shape.
     * @return the orientation.
     */
    public int[][] getOrientation() {
        return rotations[rotationIndex];
    }

    /**
     * Moves shape left if it's not blocked by another shape in the grid or out of bounds.
     * @param grid game grid
     */
    public void moveLeft(int[][] grid) {
        Position newPosition = new Position(position.getPosX() - 1, position.getPosY());
        if (!isValidPosition(grid, newPosition, getOrientation())) {
            position = newPosition;
        }
    }

    /**
     * Moves shape right if it's not blocked by another shape in the grid or out of bounds.
     * @param grid game grid
     */
    public void moveRight(int[][] grid) {
        Position newPosition = new Position(position.getPosX() + 1, position.getPosY());
        if (!isValidPosition(grid, newPosition, getOrientation())) {
            position = newPosition;
        }
    }

    /**
     * Moves shape down if it's not blocked by another shape in the grid or out of bounds.
     * @param grid game grid
     */
    public void moveDown(int[][] grid) {
        Position newPosition = new Position(position.getPosX(), position.getPosY() + 1);
        if (!isValidPosition(grid, newPosition, getOrientation())) {
            position = newPosition;
        }
    }

    /**
     * Check if shape can move down in the grid
     * @param grid game grid
     * @return true if shape can move down, otherwise false
     */
    public boolean canMoveDown(int[][] grid) {
        Position newPosition = new Position(position.getPosX(), position.getPosY() + 1);
        return !isValidPosition(grid, newPosition, getOrientation());
    }

    /**
     * Check if the current position of the shape overlaps with shapes in the grid or
     * is out of bounds.
     * @param grid game grid
     * @return true if position is valid, otherwise false
     */
    public boolean hasValidPosition(int[][] grid) {
        return isValidPosition(grid, position, getOrientation());
    }

    /**
     * Rotates shape if it's not blocked by another shape in the grid or out of bounds.
     * @param grid game grid
     */
    public void rotate(int[][] grid) {
        int newRotationIndex = (rotationIndex + 1) % 4;
        if (!isValidPosition(grid, position, rotations[newRotationIndex])) {
            rotationIndex = newRotationIndex;
        }
    }

    private boolean isValidPosition(int[][] grid, Position position, int[][] orientation) {
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
