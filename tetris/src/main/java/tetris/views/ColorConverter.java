package tetris.views;

import javafx.scene.paint.Color;
import tetris.models.BlockColor;

import java.util.HashMap;
import java.util.Map;

/**
 * This class maps colors of the game shapes to JavaFX paint colors.
 */
public class ColorConverter {
    private static Map<Integer, Color> colorMapping = new HashMap<Integer, Color>() {
        {
            put(BlockColor.PINK.ordinal(), Color.HOTPINK);
            put(BlockColor.BLUE.ordinal(), Color.CORNFLOWERBLUE);
            put(BlockColor.GREEN.ordinal(), Color.PALEGREEN);
            put(BlockColor.PURPLE.ordinal(), Color.MEDIUMPURPLE);
            put(BlockColor.YELLOW.ordinal(), Color.LEMONCHIFFON);
            put(BlockColor.ORANGE.ordinal(), Color.ORANGE);
            put(BlockColor.TURQUOISE.ordinal(), Color.MEDIUMTURQUOISE);
        }
    };

    /**
     * Converts a block color to a JavaFX paint color.
     *
     * @param color Ordinal value of the {@link BlockColor}
     * @return the converted {@link Color}
     */
    public static Color convert(int color) {
        return colorMapping.getOrDefault(color, Color.GRAY);
    }
}
