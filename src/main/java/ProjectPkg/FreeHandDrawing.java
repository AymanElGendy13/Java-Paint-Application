package ProjectPkg;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FreeHandDrawing extends Shape {
    private final List<Point> points = new ArrayList<>();

    public FreeHandDrawing(Point startPoint, Color color, boolean isDotted) {
        super(startPoint, startPoint, color, isDotted);
        points.add(startPoint);
    }

    /** Adds a new point while dragging */
    public void addPoint(Point point) {
        points.add(point);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(strokeWidth)); // Now uses strokeWidth from Shape

        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
}
