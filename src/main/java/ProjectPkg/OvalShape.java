package ProjectPkg;
import java.awt.*;

public class OvalShape extends Shape {
    private boolean isFilled;

    public OvalShape(Point startPoint, Point endPoint, Color color, boolean isFilled, boolean isDotted) {
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
            g2.fillOval(x, y, width, height);
        } else {
            g2.drawOval(x, y, width, height);
        }
    }
}
