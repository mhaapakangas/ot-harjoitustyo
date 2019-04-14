package tetris.controllers;

import lombok.Getter;
import tetris.models.Position;
import tetris.models.shapes.Shape;

import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;

public class GridService {
    @Getter
    private int[][] grid = new int[GRID_WIDTH][GRID_HEIGHT];

    public void addShapeToGrid(Shape currentShape) {
        addShapeToGrid(currentShape, grid);
    }

    public int[][] getRenderGrid(Shape currentShape) {
        int[][] renderGrid = new int[GRID_WIDTH][GRID_HEIGHT];
        for (int i = 0; i < GRID_WIDTH; i++) {
            renderGrid[i] = grid[i].clone();
        }
        addShapeToGrid(currentShape, renderGrid);
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
