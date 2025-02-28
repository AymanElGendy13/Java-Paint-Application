package ProjectPkg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileHandler {

    private static PaintCanvas canvas;

    public FileHandler(PaintCanvas canvas) {
        this.canvas = canvas;
    }

    /** Saves the current drawing as a PNG file */
    public static void saveDrawing(File file) {
        BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        canvas.paintAll(g2); // Capture the drawing
        g2.dispose();

        try {
            ImageIO.write(image, "png", file);
            JOptionPane.showMessageDialog(null, "Drawing saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving file!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /** Loads an image from a file and sets it as a background on the canvas */
    public static void loadDrawing(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            if (image == null) {
                JOptionPane.showMessageDialog(null, "Invalid image file!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            canvas.setBackgroundImage(image);
            JOptionPane.showMessageDialog(null, "Image loaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading file!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
