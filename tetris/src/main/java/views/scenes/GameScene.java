package views.scenes;

import controllers.GameService;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import models.Position;

public class GameScene {
    private Scene scene;

    public GameScene() {
        final GameService service = new GameService();
        Canvas canvas = new Canvas(300, 500);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext context = canvas.getGraphicsContext2D();
                context.setFill(Color.DARKSLATEGRAY);
                context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                Position shape = service.getShapePosition();
                context.setFill(Color.DEEPPINK);
                context.fillRect(shape.getPosX(), shape.getPosY(),
                        20, 20);
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