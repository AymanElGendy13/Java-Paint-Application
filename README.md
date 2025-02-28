# Paint Brush Application
# Overview
The Paint Brush is a Java-based drawing application built using JFrame. It allows users to draw various shapes, doodle freely, erase parts of the drawing, and manage their artwork with features like undo, redo, clear, save, and load. The application is designed to be user-friendly and provides a variety of tools to create and edit drawings.

# Features
Drawing Tools
Shapes: Draw rectangles, ovals, triangles, and lines.

Free Hand Drawing: Doodle freely using the mouse.

Eraser: Erase parts of the drawing.

Color Selection: Choose from predefined colors or open a color palette to select custom colors.

Line Style: Choose between solid, dotted, or filled shapes.

Editing Tools
Undo/Redo: Undo or redo the last action(s).
Clear All: Clear the entire drawing area.

File Handling:
Save/Load: Save your drawing as an image file or load an existing image to continue editing.

# Classes Overview
The project is structured into several classes to handle different functionalities:

PaintBrushApp: The main class that initializes the application and sets up the JFrame.

PaintCanvas: Handles the drawing area and rendering of shapes and freehand drawings.

ToolPanel: Contains the UI components for selecting tools, colors, and line styles.

Shape: A base class for all shapes (e.g., Rectangle, Oval, Triangle, Line).

Actions: Manages actions like undo, redo, and clear.

FileHandler: Handles saving and loading of drawings as image files.

# How to Use
Select a Tool: Choose a shape (rectangle, oval, triangle, line) or freehand drawing from the toolbar.

Choose a Color: Select a color from the predefined options or open the color palette for custom colors.

Adjust Line Style: Toggle between solid, dotted, or filled shapes using the checkboxes.

Draw: Click and drag on the canvas to draw shapes or doodle freely.

Erase: Use the eraser tool to remove parts of the drawing.

Undo/Redo: Use the undo and redo buttons to revert or reapply actions.

Clear All: Clear the entire canvas to start fresh.

Save/Load: Save your drawing as an image file or load an existing image to continue editing.
