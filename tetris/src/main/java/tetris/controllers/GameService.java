package tetris.controllers;

import lombok.Getter;
import tetris.models.Position;
import tetris.models.shapes.OShape;
import tetris.models.shapes.Shape;
import tetris.models.shapes.TShape;

import java.util.Random;

import static tetris.models.Constants.SHAPE_COUNT;

public class GameService {
    private Shape currentShape;
    private long lastUpdate;
    private GridService gridService;
    private boolean gameOver = false;
    @Getter
    private int score = 0;

    public GameService() {
        currentShape = createShape();
        lastUpdate = System.currentTimeMillis();
        gridService = new GridService();
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (!gameOver && currentTime - lastUpdate > 250) {
            currentShape.moveDown(gridService.getGrid());
            lastUpdate = currentTime;
            if (!currentShape.canMoveDown(gridService.getGrid())) {
                gridService.addShapeToGrid(currentShape);
                int clearedRows = gridService.clearFullRows();
                score += clearedRows * 100;

                currentShape = createShape();
                if (currentShape.isColliding(gridService.getGrid())) {
                    gameOver = true;
                }
            }
        }
    }

    private Shape createShape() {
        Random random = new Random();
        switch (random.nextInt(SHAPE_COUNT)) {
            case 0:
                return new TShape(new Position(5, 0));
            default:
                return new OShape(new Position(5, 0));
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

    public int[][] getRenderGrid() {
        return gridService.getRenderGrid(currentShape);
    }
}
