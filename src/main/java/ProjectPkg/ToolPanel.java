package ProjectPkg;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;

public class ToolPanel extends JPanel {
    private final PaintCanvas canvas;
    private final Actions actions;
    private final FileHandler fileHandler;

    public ToolPanel(PaintCanvas canvas, Actions actions, FileHandler fileHandler) {
        this.canvas = canvas;
        this.actions = actions;
        this.fileHandler = fileHandler;

        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5)); // Align items horizontally with spacing
        setBackground(Color.LIGHT_GRAY);

        // Add grouped panels
        add(createColorPanel());
        add(createShapePanel());
        add(createStylePanel());
        add(createActionsPanel());
        add(createFilePanel());
    }

    /** Creates a panel for color selection */
    private JPanel createColorPanel() {
        JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));

        colorPanel.add(createCircularColorButton(Color.RED));
        colorPanel.add(createCircularColorButton(Color.GREEN));
        colorPanel.add(createCircularColorButton(Color.BLUE));
        colorPanel.add(createCircularColorButton(Color.YELLOW));
        colorPanel.add(createCircularColorButton(Color.BLACK));
        colorPanel.add(createCircularColorButton(Color.CYAN));

        // Add the multi-colored palette button for drawing color selection
        colorPanel.add(createMultiColoredPaletteButton());

        // Add Background Color Button
        JButton bgColorButton = new JButton("Background");
        bgColorButton.addActionListener(e -> openBackgroundColorChooser());
        colorPanel.add(bgColorButton);

        return colorPanel;
    }

    /** Creates a panel for shape selection */
    private JPanel createShapePanel() {
        JPanel shapePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        shapePanel.setBorder(BorderFactory.createTitledBorder("Shapes"));

        shapePanel.add(createToolButton("Rectangle", "Rectangle"));
        shapePanel.add(createToolButton("Oval", "Oval"));
        shapePanel.add(createToolButton("Triangle", "Triangle"));
        shapePanel.add(createToolButton("Line", "Line"));
        shapePanel.add(createToolButton("Freehand", "FreeHand"));
        shapePanel.add(createToolButton("Eraser", "Eraser"));

        return shapePanel;
    }

    /** Creates a panel for style selection */
    private JPanel createStylePanel() {
        JPanel stylePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 3));
        stylePanel.setBorder(BorderFactory.createTitledBorder("Styles"));

        JRadioButton dottedButton = new JRadioButton("Dotted");
        JRadioButton filledButton = new JRadioButton("Filled");

        ButtonGroup styleGroup = new ButtonGroup(); // Only one can be selected at a time
        styleGroup.add(dottedButton);
        styleGroup.add(filledButton);

        dottedButton.addActionListener(e -> {
            canvas.setDotted(dottedButton.isSelected());
            canvas.setFilled(false);
        });

        filledButton.addActionListener(e -> {
            canvas.setFilled(filledButton.isSelected());
            canvas.setDotted(false);
        });

        // "Back to Normal" Button
        JRadioButton normalButton = new JRadioButton("Normal");
        normalButton.addActionListener(e -> {
            dottedButton.setSelected(false);
            filledButton.setSelected(false);
            // canvas.setDotted(false);
            // canvas.setFilled(false);
            styleGroup.clearSelection(); // Unselect any radio button
            canvas.setDotted(false);
            canvas.setFilled(false);
        });

        stylePanel.add(dottedButton);
        stylePanel.add(filledButton);
        stylePanel.add(normalButton); // Add the "Back to Normal" button

        return stylePanel;
    }

    /** Creates a panel for actions like undo, redo, and clear */
    private JPanel createActionsPanel() {
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 3));
        actionsPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> actions.undo());
        actionsPanel.add(undoButton);


        JButton redoButton = new JButton("Redo");
        redoButton.addActionListener(e -> actions.redo());
        actionsPanel.add(redoButton);

        JButton clearButton = new JButton("Clear All");
        clearButton.addActionListener(e -> canvas.clearCanvas());
        actionsPanel.add(clearButton);

        return actionsPanel;
    }

    /** Creates a panel for file handling (save/load) */
    private JPanel createFilePanel() {
        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        filePanel.setBorder(BorderFactory.createTitledBorder("File Handling"));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Drawing");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG Image", "png"));

            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".png");
                fileHandler.saveDrawing(file);
            }
        });
        filePanel.add(saveButton);

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Load Image");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg"));

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                fileHandler.loadDrawing(file);
            }
        });
        filePanel.add(loadButton);

        return filePanel;
    }

    /** Creates a normal tool button */
    private JButton createToolButton(String name, String tool) {
        JButton button = new JButton(name);
        button.addActionListener(e -> canvas.setSelectedTool(tool));
        return button;
    }

    /** Creates a circular color button */
    private JButton createCircularColorButton(Color color) {
        JButton button = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isArmed()) {
                    g2.setColor(color.darker());
                } else {
                    g2.setColor(color);
                }

                g2.fillOval(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(Color.BLACK);
                g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
            }
        };

        button.setPreferredSize(new Dimension(25, 25));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addActionListener(e -> canvas.setCurrentColor(color));

        return button;
    }

    /** Creates a multi-colored palette button */
    private JButton createMultiColoredPaletteButton() {
        JButton button = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create a multi-colored gradient
                GradientPaint gradient = new GradientPaint(0, 0, Color.RED, getWidth(), getHeight(), Color.BLUE);
                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());

                super.paintComponent(g);
            }
        };

        button.setPreferredSize(new Dimension(50, 30));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        button.addActionListener(e -> openColorChooser());

        return button;
    }

    /** Opens a color picker and sets the selected color */
    private void openColorChooser() {
        Color selectedColor = JColorChooser.showDialog(this, "Choose Color", Color.BLACK);
        if (selectedColor != null) {
            canvas.setCurrentColor(selectedColor);
        }
    }


    /** Opens a color picker and sets the selected background color */
    private void openBackgroundColorChooser() {
        Color selectedColor = JColorChooser.showDialog(this, "Choose Background Color", canvas.getBackground());
        if (selectedColor != null) {
            canvas.setBackground(selectedColor);
        }
    }

}