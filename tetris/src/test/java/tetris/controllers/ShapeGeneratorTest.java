package tetris.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tetris.models.Position;
import tetris.models.shapes.*;

import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static tetris.models.Constants.SHAPE_COUNT;

public class ShapeGeneratorTest {
    private ShapeGenerator shapeGenerator;
    private Random randomNumberMock;

    @Before
    public void setUp() {
        randomNumberMock = mock(Random.class);
        shapeGenerator = new ShapeGenerator(randomNumberMock);
    }

    @Test
    public void generatesTShape() {
        when(randomNumberMock.nextInt(SHAPE_COUNT)).thenReturn(0);
        Shape expected = new TShape(new Position(4, 0));

        Assert.assertEquals(expected, shapeGenerator.getNewShape());
    }

    @Test
    public void generatesLShape() {
        when(randomNumberMock.nextInt(SHAPE_COUNT)).thenReturn(1);
        Shape expected = new LShape(new Position(4, 0));

        Assert.assertEquals(expected, shapeGenerator.getNewShape());
    }

    @Test
    public void generatesSShape() {
        when(randomNumberMock.nextInt(SHAPE_COUNT)).thenReturn(2);
        Shape expected = new SShape(new Position(4, 0));

        Assert.assertEquals(expected, shapeGenerator.getNewShape());
    }

    @Test
    public void generatesZShape() {
        when(randomNumberMock.nextInt(SHAPE_COUNT)).thenReturn(3);
        Shape expected = new ZShape(new Position(4, 0));

        Assert.assertEquals(expected, shapeGenerator.getNewShape());
    }

    @Test
    public void generatesJShape() {
        when(randomNumberMock.nextInt(SHAPE_COUNT)).thenReturn(4);
        Shape expected = new JShape(new Position(4, 0));

        Assert.assertEquals(expected, shapeGenerator.getNewShape());
    }

    @Test
    public void generatesIShape() {
        when(randomNumberMock.nextInt(SHAPE_COUNT)).thenReturn(5);
        Shape expected = new IShape(new Position(3, -1));

        Assert.assertEquals(expected, shapeGenerator.getNewShape());
    }

    @Test
    public void generatesOShape() {
        when(randomNumberMock.nextInt(SHAPE_COUNT)).thenReturn(6);
        Shape expected = new OShape(new Position(4, 0));

        Assert.assertEquals(expected, shapeGenerator.getNewShape());
    }
}
