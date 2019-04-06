package tetris.views.scenes;

import tetris.controllers.GameService;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import tetris.models.Position;

import static tetris.models.Constants.BLOCK_SIZE;
import static tetris.models.Constants.GRID_HEIGHT;
import static tetris.models.Constants.GRID_WIDTH;

public class GameScene {
    private Scene scene;

    public GameScene() {
        final GameService service = new GameService();
        Canvas canvas = new Canvas(GRID_WIDTH * BLOCK_SIZE, GRID_HEIGHT * BLOCK_SIZE);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                service.update();

                GraphicsContext context = canvas.getGraphicsContext2D();
                context.setFill(Color.DARKSLATEGRAY);
                context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                Position shapePosition = service.getShapePosition();
                context.setFill(Color.DEEPPINK);
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
        };

        canvas.setFocusTraversable(true);

        canvas.addEventHandler(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent event) {
                        if (event.getCode() == KeyCode.LEFT) {
                            service.moveShapeLeft();
                        } else if (event.getCode() == KeyCode.RIGHT) {
                            service.moveShapeRight();
                        } else if (event.getCode() == KeyCode.UP) {
                            service.rotateShape();
                        }
                    }
                });

        Group root = new Group();
        root.getChildren().add(canvas);

        timer.start();
        this.scene = new Scene(root);
    }

    public Scene getScene() {
        return scene;
    }
}
