package pack4;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private int size;
    private JImageDisplay imageDisplay;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double rectangle;

    private FractalExplorer (int size) {
        this.size = size;
        this.fractalGenerator = new Mandelbrot();
        this.rectangle = new Rectangle2D.Double(0,0,0,0);
        fractalGenerator.getInitialRange(this.rectangle);
    }
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Fractals");
        JButton button = new JButton("Reset");
        imageDisplay = new JImageDisplay(size, size);
        imageDisplay.addMouseListener(new MouseListener());
        button.addActionListener(new ActionHandler());
        frame.setLayout(new java.awt.BorderLayout());
        frame.add(imageDisplay, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
    private void drawFractal() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                double xCoord = FractalGenerator.getCoord(rectangle.x, rectangle.x + rectangle.width, size, x);
                double yCoord = FractalGenerator.getCoord(rectangle.y, rectangle.y + rectangle.width, size, y);
                int count = fractalGenerator.numIterations(xCoord, yCoord);

                if (count == -1) {
                    imageDisplay.drawPixel(x, y, 0);
                }
                else {
                    float hue = 0.7f + (float) count / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    imageDisplay.drawPixel(x, y, rgbColor);
                }
            }
        }
        imageDisplay.repaint();
    }
    public class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fractalGenerator.getInitialRange(rectangle);
            drawFractal();
        }
    }
    public class MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            double x = FractalGenerator.getCoord(rectangle.x, rectangle.x + rectangle.width, size, e.getX());
            double y = FractalGenerator.getCoord(rectangle.y, rectangle.y + rectangle.width, size, e.getY());
            fractalGenerator.recenterAndZoomRange(rectangle, x, y, 0.5);
            drawFractal();
        }
    }
    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(800);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }
}
