package ProjectPkg;

import java.util.Stack;

public class Actions
{
    private final Stack<Shape> undoStack = new Stack<>();  // Stores drawn shapes for undo
    private final Stack<Shape> redoStack = new Stack<>();  // Stores undone shapes for redo
    private final PaintCanvas canvas;

    public Actions(PaintCanvas canvas)
    {
        this.canvas = canvas;
    }

    /** Adds a new shape to the undo stack and clears the redo stack */
    public void addShape(Shape shape) {
        undoStack.push(shape);
        redoStack.clear();  // Clear redo history whenever a new shape is drawn
        canvas.updateShapes(undoStack);   // Ensure UI updates instantly
    }

    /** Performs an Undo action */
    public void undo()
    {
        if (!undoStack.isEmpty()) {
            redoStack.push(undoStack.pop());
        }
        canvas.updateShapes(undoStack);

    }

    /** Performs a Redo action */
    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(redoStack.pop());
        }
        canvas.updateShapes(undoStack);
    }

    /** Clears the undo and redo stacks */
    public void clearHistory() {
        undoStack.clear();
        redoStack.clear();
        canvas.updateShapes(undoStack);
    }

    /** Returns the current list of shapes (for repainting in PaintCanvas) */
    public Stack<Shape> getShapes() {
        return undoStack;
    }
}