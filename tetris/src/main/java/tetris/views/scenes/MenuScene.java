package tetris.views.scenes;

import com.google.inject.Inject;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.Getter;

/**
 * This class builds the menu scene that displays links to other scenes.
 */
public class MenuScene {
    @Getter
    private Scene scene;

    /**
     * Constructor.
     * Creates the {@link Scene} for the main menu.
     *
     * @param sceneManager {@link SceneManager} for handling changing scenes
     */
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
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);
        layout.setId("layout");

        this.scene = new Scene(layout, 300, 250);
        scene.getStylesheets().add("Menu.css");
    }
}
