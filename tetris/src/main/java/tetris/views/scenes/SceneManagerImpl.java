package tetris.views.scenes;

import com.google.inject.Injector;
import javafx.stage.Stage;

/**
 * This class is responsible for handling scenes of the application.
 */
public class SceneManagerImpl implements SceneManager {
    private Stage stage;
    private Injector injector;

    /**
     * {@inheritDoc}
     */
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
            case SETTINGS_SCENE:
                stage.setScene(injector.getInstance(SettingsScene.class).getScene());
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void init(Injector injector, Stage stage) {
        this.injector = injector;
        this.stage = stage;
    }
}
