package ProjectPkg;
import java.awt.*;

public class TriangleShape extends Shape {
    private boolean isFilled;
    public TriangleShape(Point startPoint, Point endPoint, Color color, boolean isFilled, boolean isDotted) {
        super(startPoint, endPoint, color, isDotted);
        this.isFilled = isFilled;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        applyStrokeStyle(g2); // Apply stroke style (solid or dotted)

        // Calculate triangle vertices
        int x1 = startPoint.x;
        int y1 = startPoint.y;
        int x2 = endPoint.x;
        int y2 = startPoint.y;  // Ensures base is horizontal
        int x3 = (x1 + x2) / 2;
        int y3 = endPoint.y;  // Triangle height is determined by endPoint.y

        int[] xPoints = {x1, x2, x3};
        int[] yPoints = {y1, y2, y3};

        if (isFilled) {
            g2.fillPolygon(xPoints, yPoints, 3);
        } else {
            g2.drawPolygon(xPoints, yPoints, 3);
        }
    }
}
