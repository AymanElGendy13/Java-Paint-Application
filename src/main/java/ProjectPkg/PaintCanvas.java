package ProjectPkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Stack;

public class PaintCanvas extends JPanel {
    private List<Shape> shapes;  // Stores drawn shapes
    private Shape currentShape;                      // Currently drawn shape
    private Color currentColor = Color.BLACK;        // Default color
    private boolean isDotted = false;               // Stroke style
    private boolean isFilled = false;               // Fill option for shapes
    private String selectedTool = "FreeHand";        // Default tool
    private Actions actions;                         // Reference to Actions class
    private BufferedImage backgroundImage;

    public PaintCanvas(Actions actions) {
        this.actions = actions;
        this.shapes = (actions != null) ? actions.getShapes() : new Stack<>();  // Prevent NullPointerException
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startDrawing(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                finishDrawing(e);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateDrawing(e);
            }
        });
    }

    /** Ensures canvas repaints properly before saving */
    public void forceRepaint() {
        revalidate();
        repaint();
    }

    /** Starts drawing a shape based on the selected tool */
    private void startDrawing(MouseEvent e) {
        Point startPoint = e.getPoint();

        switch (selectedTool) {
            case "FreeHand":
                currentShape = new FreeHandDrawing(startPoint, currentColor, isDotted);
                break;
            case "Rectangle":
                currentShape = new RectangleShape(startPoint, startPoint, currentColor, isFilled, isDotted);
                break;
            case "Oval":
                currentShape = new OvalShape(startPoint, startPoint, currentColor, isFilled, isDotted);
                break;
            case "Line":
                currentShape = new LineShape(startPoint, startPoint, currentColor, isDotted);
                break;
            case "Triangle":
                currentShape = new TriangleShape(startPoint, startPoint, currentColor, isFilled, isDotted);
                break;
            case "Eraser":
                currentShape = new FreeHandDrawing(startPoint, getBackground(), false);
                currentShape.setStrokeWidth(15); // Increase eraser size
                break;
        }
        if (currentShape != null) {
            actions.addShape(currentShape);  // Add shape to actions for undo/redo
        }
        repaint();
    }

    /** Update the canvas with the new shapes list after Undo/Redo */


    public void updateShapes(Stack<Shape> newShapes) {
        this.shapes = newShapes;
        repaint();  // Instantly update the UI
    }


    /** Updates the shape as the mouse is dragged */
    private void updateDrawing(MouseEvent e) {
        if (currentShape != null) {
            if (currentShape instanceof FreeHandDrawing) {
                ((FreeHandDrawing) currentShape).addPoint(e.getPoint()); // Store each new point
            } else {
                currentShape.updateEndPoint(e.getPoint()); // Update shape size
            }
            repaint(); // Ensure UI updates while drawing
        }
    }

    /** Finalizes the shape on mouse release and adds it to the undo stack */
    private void finishDrawing(MouseEvent e) {
        if (currentShape != null) {
            currentShape.updateEndPoint(e.getPoint());
            actions.addShape(currentShape);
            currentShape = null;
            repaint();
        }
    }

    public void setBackgroundImage(BufferedImage image) {
        this.backgroundImage = image;
        repaint();
    }

    /** Renders all shapes on the canvas using Graphics2D */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Ensure background image is drawn
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // If no background image, fill with default background color
            g2.setColor(getBackground());
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        for (Shape shape : shapes) {
            shape.draw(g2);
        }
    }

    /** Clears the canvas */
   public void clearCanvas() {
        shapes.clear();
        actions.clearHistory(); // Clear undo history
        repaint();
    }

    /** Sets the current drawing color */
    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    /** Sets the current tool (Rectangle, Oval, Line, FreeHand, Eraser) */
    public void setSelectedTool(String tool) {
        this.selectedTool = tool;
    }

    /** Sets whether shapes should have a dotted stroke */
    public void setDotted(boolean isDotted) {
        this.isDotted = isDotted;
    }

    /** Sets whether shapes should be filled */
    public void setFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
        this.shapes = actions.getShapes();
    }
}