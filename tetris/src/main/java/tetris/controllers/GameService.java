package tetris.controllers;

import com.google.inject.Inject;
import lombok.Getter;

import tetris.models.Position;
import tetris.models.shapes.*;

import java.util.Random;

import static tetris.models.Constants.SHAPE_COUNT;

public class GameService {
    private Shape currentShape;
    private long lastUpdate;
    private GridService gridService;
    @Getter
    private boolean gameOver = false;
    @Getter
    private int score = 0;

    @Inject
    public GameService(GridService gridService) {
        currentShape = spawnNewShape();
        lastUpdate = System.currentTimeMillis();
        this.gridService = gridService;
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (!gameOver && currentTime - lastUpdate > 1000) {
            if (currentShape.canMoveDown(gridService.getGrid())) {
                currentShape.moveDown(gridService.getGrid());
            } else {
                clearFullRows();
                currentShape = spawnNewShape();
                if (currentShape.isColliding(gridService.getGrid())) {
                    gameOver = true;
                }
            }

            lastUpdate = currentTime;
        }
    }

    private void clearFullRows() {
        gridService.addShapeToGrid(currentShape);
        int clearedRows = gridService.clearFullRows();
        score += clearedRows * 100;
    }

    private Shape spawnNewShape() {
        Random random = new Random();
        switch (random.nextInt(SHAPE_COUNT)) {
            case 0:
                return new TShape(new Position(4, 0));
            case 1:
                return new LShape(new Position(4, 0));
            case 2:
                return new SShape(new Position(4, 0));
            case 3:
                return new ZShape(new Position(4, 0));
            case 4:
                return new JShape(new Position(4, 0));
            case 5:
                return new IShape(new Position(3, -1));
            default:
                return new OShape(new Position(4, 0));
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
