package tetris;

import com.google.inject.AbstractModule;
import tetris.controllers.LevelService;
import tetris.controllers.LevelServiceImpl;
import tetris.daos.ScoreDao;
import tetris.daos.ScoreDaoImpl;
import tetris.views.scenes.SceneManager;
import tetris.views.scenes.SceneManagerImpl;

/**
 * This class handles configurations for dependency injection.
 */
public class InjectionModule extends AbstractModule {
    /**
     * Binds interfaces to their implementations for dependency injection.
     */
    @Override
    protected void configure() {
        bind(ScoreDao.class).to(ScoreDaoImpl.class);
        bind(SceneManager.class).to(SceneManagerImpl.class).asEagerSingleton();
        bind(LevelService.class).to(LevelServiceImpl.class).asEagerSingleton();
    }
}
