package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static models.Constants.BLOCK_SIZE;
import static models.Constants.GRID_HEIGHT;
import static models.Constants.GRID_WIDTH;

@Getter
@AllArgsConstructor
public class Shape {
    private Position position;

    public void moveLeft() {
        if (position.getPosX() != 0) {
            position.setPosX(position.getPosX() - BLOCK_SIZE);
        }
    }

    public void moveRight() {
        if (position.getPosX() != (GRID_WIDTH - 1) * BLOCK_SIZE) {
            position.setPosX(position.getPosX() + BLOCK_SIZE);
        }
    }

    public void moveDown() {
        if (canFall()) {
            position.setPosY(position.getPosY() + BLOCK_SIZE);
        }
    }

    public boolean canFall() {
        return position.getPosY() != (GRID_HEIGHT - 1) * BLOCK_SIZE;
    }
}
