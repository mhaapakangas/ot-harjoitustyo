package tetris.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tetris.models.DifficultyLevel;

public class LevelServiceImplTest {
    private LevelService levelService;

    @Before
    public void setUp() {
        levelService = new LevelServiceImpl();
    }

    @Test
    public void canSetAndRetrieveLevel() {
        DifficultyLevel expected = DifficultyLevel.HARD;

        Assert.assertEquals(DifficultyLevel.EASY, levelService.getDifficultyLevel());

        levelService.setDifficultyLevel(expected);
        Assert.assertEquals(expected, levelService.getDifficultyLevel());
    }
}
