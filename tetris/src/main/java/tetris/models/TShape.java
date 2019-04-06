package tetris.models;

public class TShape extends Shape {
    public TShape(Position position) {
        this.position = position;
        this.orientations = new int[4][][];
        this.orientations[0] = new int[][]{
            {0, 1, 0},
            {1, 1, 1},
            {0, 0, 0}
        };
        this.orientations[1] = new int[][]{
            {0, 1, 0},
            {0, 1, 1},
            {0, 1, 0}
        };
        this.orientations[2] = new int[][]{
            {0, 0, 0},
            {1, 1, 1},
            {0, 1, 0}
        };
        this.orientations[3] = new int[][]{
            {0, 1, 0},
            {1, 1, 0},
            {0, 1, 0}
        };
    }


}
