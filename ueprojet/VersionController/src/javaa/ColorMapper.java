import java.awt.Color;
import java.awt.Taskbar;

public class ColorMapper {

    public static Color getColor(logisim.State state) {
        return switch (state) {
            case True -> Color.GREEN;
            case False -> Color.RED;
            case UNKNOWN -> Color.GRAY;
            case ERROR -> Color.ORANGE;
        };
    }

    public static Color getColor(Taskbar.State state) {
        return switch (state) {
            case  NORMAL -> Color.GREEN;
            case OFF -> Color.RED;
            case INDETERMINATE -> Color.GRAY;
            case ERROR -> Color.ORANGE;
		    case PAUSED -> throw new UnsupportedOperationException("Unimplemented case: " + state);
		    default -> throw new IllegalArgumentException("Unexpected value: " + state);
        };
    }
}
