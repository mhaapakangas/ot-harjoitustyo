package tetris.views.scenes;

import com.google.inject.Inject;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import lombok.Getter;
import tetris.controllers.GameService;
import tetris.controllers.ScoreService;
import tetris.views.ColorConverter;

import static tetris.models.Constants.*;

public class GameScene {
    @Getter
    private Scene scene;
    private GameService gameService;
    private ScoreService scoreService;
    private SceneManager sceneManager;
    private static final int BORDER_WIDTH = 3;
    private static final int SCORE_HEIGHT = 40;
    private static final int GAME_OVER_WIDTH = 200;
    private static final int GAME_OVER_HEIGHT = 160;

    @Inject
    public GameScene(SceneManager sceneManager, ScoreService scoreService, GameService gameService) {
        this.gameService = gameService;
        this.scoreService = scoreService;
        this.sceneManager = sceneManager;

        Group root = new Group();

        Canvas canvas = new Canvas(GRID_WIDTH * BLOCK_SIZE, SCORE_HEIGHT + GRID_HEIGHT * BLOCK_SIZE);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameService.update();

                GraphicsContext context = canvas.getGraphicsContext2D();
                drawBackground(context, canvas);
                drawScore(context, canvas, gameService.getScore(), gameService.getLevel());
                drawGrid(context);

                if (gameService.isGameOver()) {
                    drawGameOver(context, canvas, root);
                    this.stop();
                }
            }
        };

        canvas.setFocusTraversable(true);

        canvas.addEventHandler(KeyEvent.KEY_PRESSED,
            event -> {
                switch (event.getCode()) {
                    case LEFT:
                        gameService.moveShapeLeft();
                        break;
                    case RIGHT:
                        gameService.moveShapeRight();
                        break;
                    case UP:
                        gameService.rotateShape();
                        break;
                    case DOWN:
                        gameService.dropShape();
                        break;
                }
            });

        root.getChildren().addAll(canvas);

        timer.start();
        this.scene = new Scene(root);
    }

    private void drawBackground(GraphicsContext context, Canvas canvas) {
        context.setFill(Color.DARKSLATEGRAY);
        context.fillRect(0, SCORE_HEIGHT, canvas.getWidth(), canvas.getHeight());
    }

    private void drawScore(GraphicsContext context, Canvas canvas, int score, int level) {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), SCORE_HEIGHT);

        context.setFill(Color.MEDIUMPURPLE);
        context.fillRect(0, SCORE_HEIGHT - BORDER_WIDTH, canvas.getWidth(), BORDER_WIDTH);

        context.setFill(Color.LEMONCHIFFON);
        context.setTextAlign(TextAlignment.CENTER);
        context.setTextBaseline(VPos.CENTER);
        context.setFont(new Font(20));
        context.fillText(
            String.format("score: %d  level: %d", score, level),
            Math.round(canvas.getWidth() / 2),
            Math.round(20)
        );
    }

    private void drawGrid(GraphicsContext context) {
        int[][] grid = gameService.getRenderGrid();
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                if (grid[i][j] != 0) {
                    context.setFill(ColorConverter.convert(grid[i][j]));
                    context.fillRect(i * BLOCK_SIZE, SCORE_HEIGHT + j * BLOCK_SIZE,
                        BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }

    private void drawGameOver(GraphicsContext context, Canvas canvas, Group root) {
        context.setFill(Color.BLACK);
        context.fillRect(50, 200, GAME_OVER_WIDTH, GAME_OVER_HEIGHT);
        context.setFill(Color.HOTPINK);
        context.setTextAlign(TextAlignment.CENTER);
        context.setTextBaseline(VPos.CENTER);
        context.setFont(new Font(28));
        context.fillText(
            "GAME OVER",
            Math.round(canvas.getWidth() / 2),
            Math.round(220)
        );
        context.setFill(Color.LEMONCHIFFON);
        context.setTextAlign(TextAlignment.CENTER);
        context.setTextBaseline(VPos.CENTER);
        context.setFont(new Font(16));
        context.fillText(
            "Enter your name:",
            Math.round(canvas.getWidth() / 2),
            Math.round(255)
        );
        TextField usernameField = new TextField();
        usernameField.setLayoutX(65);
        usernameField.setLayoutY(275);
        Button saveScore = new Button("Save score");
        saveScore.setLayoutX(108);
        saveScore.setLayoutY(315);
        saveScore.setOnAction(e -> {
            scoreService.saveScore(gameService.getScore(), usernameField.getText().trim());
            sceneManager.setScene(AppScene.HIGH_SCORE_SCENE);
        });

        BooleanBinding binding = new BooleanBinding() {
            {
                super.bind(usernameField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return usernameField.getText().trim().isEmpty();
            }
        };
        saveScore.disableProperty().bind(binding);

        root.getChildren().addAll(usernameField, saveScore);
    }
}
