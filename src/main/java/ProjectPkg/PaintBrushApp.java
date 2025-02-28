package ProjectPkg;

import javax.swing.*;
import java.awt.*;

public class PaintBrushApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Paint Brush");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLayout(new BorderLayout());

            PaintCanvas canvas = new PaintCanvas(null); // Create PaintCanvas instance
            Actions actions = new Actions(canvas); // Pass PaintCanvas to Actions
            canvas.setActions(actions); // Set Actions in PaintCanvas
            FileHandler fileHandler = new FileHandler(canvas);
            ToolPanel toolPanel = new ToolPanel(canvas, actions, fileHandler);

            // Wrap ToolPanel in JScrollPane for horizontal scrolling
            JScrollPane scrollPane = new JScrollPane(toolPanel,
                    JScrollPane.VERTICAL_SCROLLBAR_NEVER,  // Disable vertical scrolling
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Enable horizontal scrolling

            scrollPane.setPreferredSize(new Dimension(1200, 80)); // Adjust height if needed

            // Add components to frame
            frame.add(scrollPane, BorderLayout.NORTH); // Add scrollable toolbar at the top
            frame.add(canvas, BorderLayout.CENTER); // Add canvas in the center

            frame.setVisible(true);
        });
    }
}
