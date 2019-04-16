package tetris.views.scenes;

import com.google.inject.Inject;
import javafx.animation.AnimationTimer;
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
import tetris.controllers.GameService;
import tetris.controllers.ScoreService;
import tetris.models.BlockColor;

import java.util.HashMap;
import java.util.Map;

import static tetris.models.Constants.*;

public class GameScene {
    private Scene scene;
    private GameService gameService;
    private ScoreService scoreService;
    private SceneManager sceneManager;
    private Map<Integer, Color> colorMapping;
    private static final int SCORE_HEIGHT = 40;
    private static final int GAME_OVER_WIDTH = 200;
    private static final int GAME_OVER_HEIGHT = 160;

    @Inject
    public GameScene(SceneManager sceneManager, ScoreService scoreService, GameService gameService) {
        this.gameService = gameService;
        this.scoreService = scoreService;
        this.sceneManager = sceneManager;

        Group root = new Group();
        colorMapping = new HashMap<>();
        colorMapping.put(BlockColor.PINK.ordinal(), Color.HOTPINK);
        colorMapping.put(BlockColor.BLUE.ordinal(), Color.CORNFLOWERBLUE);
        colorMapping.put(BlockColor.GREEN.ordinal(), Color.MEDIUMSPRINGGREEN);
        colorMapping.put(BlockColor.PURPLE.ordinal(), Color.MEDIUMPURPLE);
        colorMapping.put(BlockColor.YELLOW.ordinal(), Color.YELLOW);
        colorMapping.put(BlockColor.ORANGE.ordinal(), Color.DARKORANGE);
        colorMapping.put(BlockColor.TURQUOISE.ordinal(), Color.MEDIUMTURQUOISE);

        Canvas canvas = new Canvas(GRID_WIDTH * BLOCK_SIZE, SCORE_HEIGHT + GRID_HEIGHT * BLOCK_SIZE);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameService.update();

                GraphicsContext context = canvas.getGraphicsContext2D();
                drawBackground(context, canvas);
                drawScore(context, canvas, gameService.getScore());
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

    private void drawScore(GraphicsContext context, Canvas canvas, int score) {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), SCORE_HEIGHT);
        context.setFill(Color.WHITE);
        context.setTextAlign(TextAlignment.CENTER);
        context.setTextBaseline(VPos.CENTER);
        context.setFont(new Font(20));
        context.fillText(
            "Current score: " + score,
            Math.round(canvas.getWidth() / 2),
            Math.round(20)
        );
    }

    private void drawGrid(GraphicsContext context) {
        int[][] grid = gameService.getRenderGrid();
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                if (grid[i][j] != 0) {
                    context.setFill(colorMapping.getOrDefault(grid[i][j], Color.GRAY));
                    context.fillRect(i * BLOCK_SIZE, SCORE_HEIGHT + j * BLOCK_SIZE,
                        BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }

    private void drawGameOver(GraphicsContext context, Canvas canvas, Group root) {
        context.setFill(Color.BLACK);
        context.fillRect(50, 200, GAME_OVER_WIDTH, GAME_OVER_HEIGHT);
        context.setFill(Color.RED);
        context.setTextAlign(TextAlignment.CENTER);
        context.setTextBaseline(VPos.CENTER);
        context.setFont(new Font(28));
        context.fillText(
            "Game over",
            Math.round(canvas.getWidth() / 2),
            Math.round(218)
        );
        context.setFill(Color.WHITE);
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
            scoreService.saveScore(gameService.getScore(), usernameField.getText());
            sceneManager.setScene(AppScene.MENU_SCENE);
        });

        root.getChildren().addAll(usernameField, saveScore);
    }

    public Scene getScene() {
        return scene;
    }
}
