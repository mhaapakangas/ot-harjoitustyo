package tetris.views.scenes;

import com.google.inject.Injector;
import javafx.stage.Stage;

public interface SceneManager {
    void setScene(AppScene scene);
    void init(Injector injector, Stage stage);
}
