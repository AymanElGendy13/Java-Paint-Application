package ProjectPkg;
import java.awt.*;

public class LineShape extends Shape {
    public LineShape(Point startPoint, Point endPoint, Color color, boolean isDotted) {
        super(startPoint, endPoint, color, isDotted);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        applyStrokeStyle(g2); // Apply dotted or solid stroke

        g2.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
}
