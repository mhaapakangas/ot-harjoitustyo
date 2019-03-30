package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static models.Constants.GRID_HEIGHT;
import static models.Constants.GRID_WIDTH;

@Getter
@AllArgsConstructor
public class Shape {
    private Position position;

    public void moveLeft(boolean[][] grid) {
        if (position.getGridPosX() != 0 &&
                !grid[position.getGridPosX() - 1][position.getGridPosY()]) {
            position.setGridPosX(position.getGridPosX() - 1);
        }
    }

    public void moveRight(boolean[][] grid) {
        if (position.getGridPosX() != GRID_WIDTH - 1  &&
                !grid[position.getGridPosX() + 1][position.getGridPosY()]) {
            position.setGridPosX(position.getGridPosX() + 1);
        }
    }

    public void moveDown(boolean[][] grid) {
        if (canFall(grid)) {
            position.setGridPosY(position.getGridPosY() + 1);
        }
    }

    public boolean canFall(boolean[][] grid) {
        if (position.getGridPosY() == GRID_HEIGHT - 1) {
            return false;
        }

        boolean isColliding = grid[position.getGridPosX()][position.getGridPosY() + 1];
        return !isColliding;
    }
}
