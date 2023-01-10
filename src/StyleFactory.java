import java.awt.Color;

public class StyleFactory {
    public static Style getStyle(StyleType level) {
        return switch (level) {
            case H1 -> new Style(0, Color.red, 48, 20);
            case H2 -> new Style(20, Color.blue, 40, 10);
            case H3 -> new Style(50, Color.black, 36, 10);
            case H4 -> new Style(70, Color.black, 30, 10);
            case P -> new Style(90, Color.black, 24, 10);
            default -> null;
        };
    }
}
