package tetris.views.scenes;

import com.google.inject.Injector;
import javafx.stage.Stage;

/**
 * Interface for handling scenes of the application.
 */
public interface SceneManager {

    /**
     * Sets a scene to the stage.
     *
     * @param scene scene to display
     */
    void setScene(AppScene scene);

    /**
     * Initializes the scene manager.
     *
     * @param injector {@link Injector} for dependency injection
     * @param stage primary stage of the application
     */
    void init(Injector injector, Stage stage);
}
