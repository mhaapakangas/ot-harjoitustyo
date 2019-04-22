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
        currentShape = createShape();
        lastUpdate = System.currentTimeMillis();
        this.gridService = gridService;
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

    public int[][] getRenderGrid() {
        return gridService.getRenderGrid(currentShape);
    }
}
