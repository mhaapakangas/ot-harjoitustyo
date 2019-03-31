package models;

public class OShape extends Shape {
    public OShape(Position position) {
        this.position = position;
        this.orientation = new int[][]{ {1, 1},
                                        {1, 1} };
    }
}
