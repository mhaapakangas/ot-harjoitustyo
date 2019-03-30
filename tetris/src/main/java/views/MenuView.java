package views;

import javafx.application.Application;
import javafx.stage.Stage;
import views.scenes.MenuScene;

public class MenuView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new MenuScene(primaryStage).getScene());
        primaryStage.show();
    }
}
