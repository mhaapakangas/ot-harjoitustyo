package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Shape {
    private Position position;

    public void moveLeft() {
        position.setPosX(position.getPosX() - 20);
    }

    public void moveRight() {
        position.setPosX(position.getPosX() + 20);
    }
}
