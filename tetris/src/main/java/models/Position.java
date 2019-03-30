package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static models.Constants.BLOCK_SIZE;

@Getter
@Setter
@AllArgsConstructor
public class Position {
    private int posX;
    private int posY;

    public int getGridPosX() {
        return posX / BLOCK_SIZE;
    }

    public int getGridPosY() {
        return posY / BLOCK_SIZE;
    }
}
