package tetris.models;

public class OShape extends Shape {
    public OShape(Position position) {
        this.position = position;
        this.orientations = new int[4][][];
        this.orientations[0] = new int[][]{
            {1, 1},
            {1, 1}
        };
        this.orientations[1] = this.orientations[0];
        this.orientations[2] = this.orientations[0];
        this.orientations[3] = this.orientations[0];
    }
}
