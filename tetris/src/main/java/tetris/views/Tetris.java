package tetris.views;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.stage.Stage;
import tetris.InjectionModule;
import tetris.daos.DatabaseService;
import tetris.views.scenes.AppScene;
import tetris.views.scenes.SceneManager;

import java.sql.SQLException;

public class Tetris extends Application {
    private SceneManager sceneManager;
    private Injector injector;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws SQLException, ClassNotFoundException {
        this.injector = Guice.createInjector(new InjectionModule());
        sceneManager = injector.getInstance(SceneManager.class);
        DatabaseService service = new DatabaseService();
        service.init();
    }

    public void start(Stage primaryStage) {
        sceneManager.init(injector, primaryStage);
        sceneManager.setScene(AppScene.MENU_SCENE);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
