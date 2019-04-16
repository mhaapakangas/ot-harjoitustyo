package tetris.views.scenes;

import com.google.inject.Inject;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import tetris.controllers.ScoreService;
import tetris.models.Score;

import java.util.List;

public class HighScoreScene {
    private Scene scene;

    @Inject
    public HighScoreScene(ScoreService scoreService, SceneManager sceneManager) {
        Button backToMenuButton = new Button("Back to Menu");
        backToMenuButton.setOnAction(e -> {
            sceneManager.setScene(AppScene.MENU_SCENE);
        });

        TableView table = new TableView();
        TableColumn playerCol = new TableColumn("Player");
        playerCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn scoreCol = new TableColumn("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

        table.getColumns().addAll(playerCol, scoreCol);
        List<Score> scores = scoreService.getHighScores();
        scores.forEach(score -> table.getItems().add(score));

        VBox root = new VBox();
        root.getChildren().addAll(backToMenuButton, table);

        this.scene = new Scene(root);
    }

    public Scene getScene() {
        return scene;
    }
}