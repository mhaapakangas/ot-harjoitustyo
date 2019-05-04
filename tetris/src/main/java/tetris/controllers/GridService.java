package tetris.controllers;

import lombok.Getter;
import tetris.models.Position;
import tetris.models.shapes.Shape;

import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;

/**
 * This class is responsible for modifying the game grid.
 *
 */
public class GridService {
    /**
     * A 2D-array of int representing the game state. Empty cells in
     * the game grid have the value of 0. Cells that are occupied by blocks
     * have the ordinal value of a {@link tetris.models.BlockColor}
     */
    @Getter
    private int[][] grid = new int[GRID_WIDTH][GRID_HEIGHT];

    /**
     * Adds a new shape to the game grid.
     * @param shape shape to add
     */
    public void addShapeToGrid(Shape shape) {
        addShapeToGrid(shape, grid);
    }

    /**
     * Creates and returns a new grid from game grid and a shape.
     * @param shape shape to add to grid
     * @return the new grid.
     */
    public int[][] getRenderGrid(Shape shape) {
        int[][] renderGrid = new int[GRID_WIDTH][GRID_HEIGHT];
        for (int i = 0; i < GRID_WIDTH; i++) {
            renderGrid[i] = grid[i].clone();
        }
        addShapeToGrid(shape, renderGrid);
        return renderGrid;
    }

    private void addShapeToGrid(Shape currentShape, int[][] targetGrid) {
        int[][] shapeOrientation = currentShape.getOrientation();
        Position shapePosition = currentShape.getPosition();
        for (int i = 0; i < shapeOrientation.length; i++) {
            for (int j = 0; j < shapeOrientation[0].length; j++) {
                if (shapeOrientation[j][i] != 0) {
                    targetGrid[shapePosition.getPosX() + i][shapePosition.getPosY() + j]
                        = currentShape.getColor().ordinal();
                }
            }
        }
    }

    /**
     * Counts and clears full rows from game grid. Moves rows above cleared
     * ones down.
     * @return the number of cleared rows.
     */
    public int clearFullRows() {
        int clearedRows = 0;
        rows:
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (grid[j][i] == 0) {
                    continue rows;
                }
            }
            clearRow(i);
            clearedRows++;
        }

        return clearedRows;
    }

    private void clearRow(int rowIndex) {
        for (int i = rowIndex; i > 0; i--) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                grid[j][i] = grid[j][i - 1];
            }
        }

        for (int j = 0; j < GRID_WIDTH; j++) {
            grid[j][0] = 0;
        }
    }
}
