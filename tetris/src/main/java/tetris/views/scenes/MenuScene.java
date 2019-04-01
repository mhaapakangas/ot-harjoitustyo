package tetris.views.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuScene {
    private Scene scene;

    public MenuScene(Stage stage) {
        Button startGameButton = new Button("New game");
        startGameButton.setOnAction(e->{
            stage.setScene(new GameScene().getScene());
        });

        Button highScoresButton = new Button("High scores");
        highScoresButton.setDisable(true);

        VBox layout = new VBox(startGameButton, highScoresButton);
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);

        this.scene = new Scene(layout, 300, 250);
    }

    public Scene getScene() {
        return scene;
    }
}
