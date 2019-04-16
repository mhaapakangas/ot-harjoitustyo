package tetris;

import com.google.inject.AbstractModule;
import tetris.daos.ScoreDao;
import tetris.daos.ScoreDaoImpl;
import tetris.views.scenes.SceneManager;
import tetris.views.scenes.SceneManagerImpl;

public class InjectionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ScoreDao.class).to(ScoreDaoImpl.class);
        bind(SceneManager.class).to(SceneManagerImpl.class).asEagerSingleton();
    }
}