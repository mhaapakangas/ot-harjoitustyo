package tetris.views.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import tetris.controllers.GameService;
import tetris.models.Position;

import static tetris.models.Constants.*;

public class GameScene {
    private Scene scene;
    private GameService service;

    public GameScene() {
        service = new GameService();
        Canvas canvas = new Canvas(GRID_WIDTH * BLOCK_SIZE, GRID_HEIGHT * BLOCK_SIZE);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                service.update();

                GraphicsContext context = canvas.getGraphicsContext2D();
                drawBackground(context, canvas);
                drawCurrentShape(context);
                drawGrid(context);
            }
        };

        canvas.setFocusTraversable(true);

        canvas.addEventHandler(KeyEvent.KEY_PRESSED,
            event -> {
                switch (event.getCode()) {
                    case LEFT:
                        service.moveShapeLeft();
                        break;
                    case RIGHT:
                        service.moveShapeRight();
                        break;
                    case UP:
                        service.rotateShape();
                        break;
                }
            });

        Group root = new Group();
        root.getChildren().add(canvas);

        timer.start();
        this.scene = new Scene(root);
    }

    private void drawBackground(GraphicsContext context, Canvas canvas) {
        context.setFill(Color.DARKSLATEGRAY);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawCurrentShape(GraphicsContext context) {
        context.setFill(Color.DEEPPINK);
        Position shapePosition = service.getShapePosition();
        int[][] shapeOrientation = service.getShapeOrientation();

        for (int i = 0; i < shapeOrientation.length; i++) {
            for (int j = 0; j < shapeOrientation[0].length; j++) {
                if (shapeOrientation[j][i] != 0) {
                    context.fillRect((shapePosition.getPosX() + i) * BLOCK_SIZE,
                        (shapePosition.getPosY() + j)  * BLOCK_SIZE,
                        BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }

    private void drawGrid(GraphicsContext context) {
        context.setFill(Color.DARKMAGENTA);
        int[][] grid = service.getGrid();
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                if (grid[i][j] != 0) {
                    context.fillRect(i * BLOCK_SIZE, j * BLOCK_SIZE,
                        BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }

    public Scene getScene() {
        return scene;
    }
}
