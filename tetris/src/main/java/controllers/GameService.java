package controllers;

import models.Position;
import models.Shape;

import static models.Constants.GRID_HEIGHT;
import static models.Constants.GRID_WIDTH;

public class GameService {
    private Shape currentShape;
    private boolean[][] grid = new boolean[GRID_WIDTH][GRID_HEIGHT];

    public GameService() {
        this.currentShape = new Shape(new Position(150, 0));
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
