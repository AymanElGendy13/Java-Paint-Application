package ProjectPkg;
import java.awt.*;

public abstract class Shape {
    protected Point startPoint;
    protected Point endPoint;
    protected Color color;
    protected boolean isDotted;
    protected float strokeWidth = 2.0f; // Default stroke width

    public Shape(Point startPoint, Point endPoint, Color color, boolean isDotted) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.color = color;
        this.isDotted = isDotted;
    }

    /** Abstract method to be implemented by subclasses for drawing */
    public abstract void draw(Graphics2D g2);

    /** Updates the end point of the shape while dragging */
    public void updateEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    /** Applies stroke style (solid or dotted) */
    protected void applyStrokeStyle(Graphics2D g2) {
        if (isDotted) {
            float[] dashPattern = {5, 5};
            g2.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        } else {
            g2.setStroke(new BasicStroke(strokeWidth));
        }
    }

    /** Getter and Setter for stroke width */
    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

}

