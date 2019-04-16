package tetris.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Position {
    private int posX;
    private int posY;
}
