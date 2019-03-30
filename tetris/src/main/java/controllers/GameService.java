package controllers;

import models.Position;
import models.Shape;

public class GameService {
    private Shape currentShape;

    public GameService() {
        this.currentShape = new Shape(new Position(150, 100));
    }

    public void moveShapeLeft() {
        this.currentShape.moveLeft();
    }

    public void moveShapeRight() {
        this.currentShape.moveRight();
    }

    public Position getShapePosition() {
        return this.currentShape.getPosition();
    }
}
