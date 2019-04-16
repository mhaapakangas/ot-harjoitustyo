package tetris.views.scenes;

import com.google.inject.Injector;
import javafx.stage.Stage;

public class SceneManagerImpl implements SceneManager {
    private Stage stage;
    private Injector injector;

    public void setScene(AppScene scene) {
        switch (scene) {
            case GAME_SCENE:
                stage.setScene(injector.getInstance(GameScene.class).getScene());
                break;
            case MENU_SCENE:
                stage.setScene(injector.getInstance(MenuScene.class).getScene());
                break;
            case HIGH_SCORE_SCENE:
                stage.setScene(injector.getInstance(HighScoreScene.class).getScene());
                break;
        }
    }

    public void init(Injector injector, Stage stage) {
        this.injector = injector;
        this.stage = stage;
    }
}
