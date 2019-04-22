package tetris.views.scenes;

import com.google.inject.Inject;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import lombok.Getter;

public class MenuScene {
    @Getter
    private Scene scene;

    @Inject
    public MenuScene(SceneManager sceneManager) {
        Button startGameButton = new Button("New game");
        startGameButton.setOnAction(e -> {
            sceneManager.setScene(AppScene.GAME_SCENE);
        });

        Button highScoresButton = new Button("High scores");
        highScoresButton.setOnAction(e -> {
            sceneManager.setScene(AppScene.HIGH_SCORE_SCENE);
        });

        VBox layout = new VBox(startGameButton, highScoresButton);
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);

        this.scene = new Scene(layout, 300, 250);
    }
}
