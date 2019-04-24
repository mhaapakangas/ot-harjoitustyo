package tetris.views.scenes;

import com.google.inject.Inject;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import lombok.Getter;
import tetris.controllers.ScoreService;
import tetris.models.Score;

import java.util.List;

public class HighScoreScene {
    @Getter
    private Scene scene;

    @Inject
    public HighScoreScene(ScoreService scoreService, SceneManager sceneManager) {
        Text title = new Text();
        title.setText("HIGH SCORES");
        title.setId("title");

        Button backToMenuButton = new Button("Back to Menu");
        backToMenuButton.setOnAction(e -> {
            sceneManager.setScene(AppScene.MENU_SCENE);
        });

        TableView table = new TableView();
        table.setEditable(false);
        table.setMaxSize(300, 350);
        List<Score> scores = scoreService.getHighScores();
        scores.forEach(score -> table.getItems().add(score));

        TableColumn rankCol = new TableColumn("");
        rankCol.setSortable(false);
        rankCol.setResizable(false);
        rankCol.setPrefWidth(30);
        rankCol.setId("rankCol");
        rankCol.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Score, String>, ObservableValue<String>>) p ->
            new ReadOnlyObjectWrapper(Integer.toString(table.getItems().indexOf(p.getValue()) + 1)));

        TableColumn playerCol = new TableColumn("Player");
        playerCol.setSortable(false);
        playerCol.setResizable(false);
        playerCol.setPrefWidth(110);
        playerCol.setId("playerCol");
        playerCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn scoreCol = new TableColumn("Score");
        scoreCol.setSortable(false);
        scoreCol.setResizable(false);
        scoreCol.setPrefWidth(110);
        scoreCol.setId("scoreCol");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

        table.getColumns().addAll(rankCol, playerCol, scoreCol);

        VBox layout = new VBox();
        layout.getChildren().addAll(title, table, backToMenuButton);
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);
        layout.setId("layout");
        this.scene = new Scene(layout);
        scene.getStylesheets().add("HighScores.css");
    }
}
