package tetris.controllers;

import com.google.inject.Inject;
import lombok.Getter;
import tetris.models.shapes.Shape;

import static tetris.models.Constants.ROWS_PER_LEVEL;

public class GameService {
    private Shape currentShape;
    private long lastUpdate;
    private GridService gridService;
    private ShapeGenerator shapeGenerator;
    @Getter
    private boolean gameOver = false;
    @Getter
    private int score = 0;
    @Getter
    private int level = 0;
    private int linesClearedAtCurrentLevel = 0;

    @Inject
    public GameService(GridService gridService, ShapeGenerator shapeGenerator) {
        lastUpdate = System.currentTimeMillis();
        this.gridService = gridService;
        this.shapeGenerator = shapeGenerator;
        currentShape = shapeGenerator.getNewShape();
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (!gameOver && currentTime - lastUpdate > (800 * Math.pow(0.9, level))) {
            if (currentShape.canMoveDown(gridService.getGrid())) {
                currentShape.moveDown(gridService.getGrid());
            } else {
                gridService.addShapeToGrid(currentShape);

                clearFullRows();

                currentShape = shapeGenerator.getNewShape();
                if (currentShape.isColliding(gridService.getGrid())) {
                    gameOver = true;
                }
            }

            lastUpdate = currentTime;
        }
    }

    private void clearFullRows() {
        int clearedRows = gridService.clearFullRows();
        updateScore(clearedRows);
        updateLevel(clearedRows);
    }

    private void updateScore(int clearedRows) {
        int points;
        switch (clearedRows) {
            case 1:
                points = 40;
                break;
            case 2:
                points = 100;
                break;
            case 3:
                points = 300;
                break;
            case 4:
                points = 1200;
                break;
            default:
                points = 0;
        }

        score += points * (level + 1);
    }

    private void updateLevel(int clearedRows) {
        linesClearedAtCurrentLevel += clearedRows;

        if (linesClearedAtCurrentLevel >= ROWS_PER_LEVEL) {
            level++;
            linesClearedAtCurrentLevel -= ROWS_PER_LEVEL;
        }
    }

    public void moveShapeLeft() {
        currentShape.moveLeft(gridService.getGrid());
    }

    public void moveShapeRight() {
        currentShape.moveRight(gridService.getGrid());
    }

    public void rotateShape() {
        currentShape.rotate(gridService.getGrid());
    }

    public void dropShape() {
        while (currentShape.canMoveDown(gridService.getGrid())) {
            currentShape.moveDown(gridService.getGrid());
        }
    }

    public int[][] getRenderGrid() {
        return gridService.getRenderGrid(currentShape);
    }
}
