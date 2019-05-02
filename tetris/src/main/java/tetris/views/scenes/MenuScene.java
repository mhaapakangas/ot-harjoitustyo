package tetris.views.scenes;

import com.google.inject.Inject;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.Getter;

public class MenuScene {
    @Getter
    private Scene scene;

    @Inject
    public MenuScene(SceneManager sceneManager) {
        Text title = new Text();
        title.setText("TETRIS");
        title.setId("title");

        Button startGameButton = new Button("New game");
        startGameButton.setOnAction(e -> {
            sceneManager.setScene(AppScene.GAME_SCENE);
        });

        Button highScoresButton = new Button("High scores");
        highScoresButton.setOnAction(e -> {
            sceneManager.setScene(AppScene.HIGH_SCORE_SCENE);
        });

        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction(e -> {
            sceneManager.setScene(AppScene.SETTINGS_SCENE);
        });

        VBox layout = new VBox(title, startGameButton, highScoresButton, settingsButton);
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);
        layout.setId("layout");

        this.scene = new Scene(layout, 300, 250);
        scene.getStylesheets().add("Menu.css");
    }
}
