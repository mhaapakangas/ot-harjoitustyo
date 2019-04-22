package tetris.views;

import javafx.scene.paint.Color;
import tetris.models.BlockColor;

import java.util.HashMap;
import java.util.Map;

public class ColorConverter {
    private static Map<Integer, Color> colorMapping = new HashMap<Integer, Color>() {{
        put(BlockColor.PINK.ordinal(), Color.HOTPINK);
        put(BlockColor.BLUE.ordinal(), Color.CORNFLOWERBLUE);
        put(BlockColor.GREEN.ordinal(), Color.MEDIUMSPRINGGREEN);
        put(BlockColor.PURPLE.ordinal(), Color.MEDIUMPURPLE);
        put(BlockColor.YELLOW.ordinal(), Color.YELLOW);
        put(BlockColor.ORANGE.ordinal(), Color.DARKORANGE);
        put(BlockColor.TURQUOISE.ordinal(), Color.MEDIUMTURQUOISE);
    }};

    public static Color convert(int color) {
        return colorMapping.getOrDefault(color, Color.GRAY);
    }
}
