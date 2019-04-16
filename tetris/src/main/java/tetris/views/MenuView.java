package tetris.views;

import javafx.application.Application;
import javafx.stage.Stage;
import tetris.controllers.ScoreService;
import tetris.daos.DatabaseService;
import tetris.views.scenes.MenuScene;

import java.sql.SQLException;

public class MenuView extends Application {
    private ScoreService scoreService;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws SQLException, ClassNotFoundException {
        DatabaseService service = new DatabaseService();
        service.init();
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new MenuScene(primaryStage).getScene());
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
