package ProjectPkg;
import java.awt.*;

public class RectangleShape extends Shape {
    private final boolean isFilled;

    public RectangleShape(Point startPoint, Point endPoint, Color color, boolean isFilled, boolean isDotted) {
        super(startPoint, endPoint, color, isDotted);
        this.isFilled = isFilled;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        applyStrokeStyle(g2);  // Apply dotted or solid stroke

        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);

        if (isFilled) {
            g2.fillRect(x, y, width, height);
        } else {
            g2.drawRect(x, y, width, height);
        }
    }
}
