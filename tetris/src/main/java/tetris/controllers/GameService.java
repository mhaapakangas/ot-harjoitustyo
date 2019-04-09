package tetris.controllers;

import tetris.models.Position;
import tetris.models.shapes.Shape;
import tetris.models.shapes.TShape;

public class GameService {
    private Shape currentShape;
    private long lastUpdate;
    private GridService gridService;

    public GameService() {
        currentShape = new TShape(new Position(5, 0));
        lastUpdate = System.currentTimeMillis();
        gridService = new GridService();
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > 250) {
            currentShape.moveDown(gridService.getGrid());
            lastUpdate = currentTime;
            if (!currentShape.canMoveDown(gridService.getGrid())) {
                gridService.addShapeToGrid(currentShape);
                gridService.clearFullRows();
                currentShape = new TShape(new Position(5, 0));
            }
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
