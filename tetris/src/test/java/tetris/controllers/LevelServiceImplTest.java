package tetris.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tetris.models.Level;

public class LevelServiceImplTest {
    private LevelService levelService;

    @Before
    public void setUp() {
        levelService = new LevelServiceImpl();
    }

    @Test
    public void canSetAndRetrieveLevel() {
        Level expected = Level.HARD;

        Assert.assertEquals(Level.EASY, levelService.getLevel());

        levelService.setLevel(expected);
        Assert.assertEquals(expected, levelService.getLevel());
    }
}
