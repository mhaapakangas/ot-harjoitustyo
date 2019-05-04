package tetris.controllers;

import com.google.inject.Inject;
import lombok.Getter;
import tetris.models.shapes.Shape;

import static tetris.models.Constants.ROWS_PER_LEVEL;

/**
 * This class is responsible for maintaining and updating the game state.
 */
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
    private int level;
    private int linesClearedAtCurrentLevel = 0;

    @Inject
    public GameService(GridService gridService, ShapeGenerator shapeGenerator, LevelService levelService) {
        lastUpdate = System.currentTimeMillis();
        this.gridService = gridService;
        this.level = levelService.getDifficultyLevel().getLevel();
        this.shapeGenerator = shapeGenerator;
        currentShape = shapeGenerator.getNewShape();
    }

    /**
     * This method updates the game state if game is ongoing and
     * game update rate is reached.
     *
     * It moves the current shape down if possible. Otherwise it
     * clears full rows, updates the score and spawns a new shape.
     *
     */
    public void update() {
        long currentTime = System.currentTimeMillis();
        if (!gameOver && isUpdateNeeded(currentTime)) {
            if (currentShape.canMoveDown(gridService.getGrid())) {
                currentShape.moveDown(gridService.getGrid());
            } else {
                gridService.addShapeToGrid(currentShape);

                clearFullRows();

                currentShape = shapeGenerator.getNewShape();
                if (!currentShape.hasValidPosition(gridService.getGrid())) {
                    gameOver = true;
                }
            }

            lastUpdate = currentTime;
        }
    }

    private boolean isUpdateNeeded(long currentTime) {
        return currentTime - lastUpdate > getUpdateInterval();
    }

    private double getUpdateInterval() {
        return 800 * Math.pow(0.9, level);
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

    /**
     * Moves current shape left if possible.
     */
    public void moveShapeLeft() {
        currentShape.moveLeft(gridService.getGrid());
    }

    /**
     * Moves current shape right if possible.
     */
    public void moveShapeRight() {
        currentShape.moveRight(gridService.getGrid());
    }

    /**
     * Rotates current shape if possible.
     */
    public void rotateShape() {
        currentShape.rotate(gridService.getGrid());
    }

    /**
     * Moves current shape down until it hits an obstacle.
     */
    public void dropShape() {
        while (currentShape.canMoveDown(gridService.getGrid())) {
            currentShape.moveDown(gridService.getGrid());
        }
    }

    /**
     * Returns a color grid of game state for rendering
     * @return the grid.
     */
    public int[][] getRenderGrid() {
        return gridService.getRenderGrid(currentShape);
    }
}
