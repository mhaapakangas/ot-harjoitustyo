package tetris.views.scenes;

import com.google.inject.Inject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.Getter;
import tetris.controllers.LevelService;
import tetris.models.Level;

public class SettingsScene {
    @Getter
    private Scene scene;

    @Inject
    public SettingsScene(SceneManager sceneManager, LevelService levelService) {
        Text title = new Text();
        title.setText("SETTINGS");
        title.setId("title");

        ToggleGroup difficultyLevels = new ToggleGroup();
        RadioButton easyButton = new RadioButton("easy");
        easyButton.setToggleGroup(difficultyLevels);
        easyButton.setUserData(Level.EASY);

        RadioButton mediumButton = new RadioButton("medium");
        mediumButton.setToggleGroup(difficultyLevels);
        mediumButton.setUserData(Level.MEDIUM);

        RadioButton hardButton = new RadioButton("hard");
        hardButton.setToggleGroup(difficultyLevels);
        hardButton.setUserData(Level.HARD);

        switch (levelService.getLevel()) {
            case HARD:
                hardButton.setSelected(true);
                break;
            case MEDIUM:
                mediumButton.setSelected(true);
                break;
            default:
                easyButton.setSelected(true);
        }

        difficultyLevels.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (difficultyLevels.getSelectedToggle() != null) {
                    Level newLevel = (Level) difficultyLevels.getSelectedToggle().getUserData();
                    levelService.setLevel(newLevel);
                }
            }
        });

        Button backToMenuButton = new Button("Back to Menu");
        backToMenuButton.setOnAction(e -> {
            sceneManager.setScene(AppScene.MENU_SCENE);
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(title, easyButton, mediumButton, hardButton, backToMenuButton);
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);
        layout.setId("layout");
        this.scene = new Scene(layout);
    }
}
