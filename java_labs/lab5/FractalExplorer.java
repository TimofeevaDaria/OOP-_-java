
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FractalExplorer {
    private int size;
    private JImageDisplay imageDisplay; // Ссылка JImageDisplay для обновления отображения из различных методов
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double rectangle;

    private FractalExplorer (int size) {
        this.size = size;
        this.fractalGenerator = new Mandelbrot();
        this.rectangle = new Rectangle2D.Double(0,0,0,0);
        fractalGenerator.getInitialRange(this.rectangle);
    }
    String[] fractals = {"Mandelbrot", "Tricorn", "BurningShip"};
    public void createAndShowGUI() {

        JFrame frame = new JFrame("Fractals");
        frame.setLayout(new java.awt.BorderLayout());

        imageDisplay = new JImageDisplay(size, size);
        imageDisplay.addMouseListener(new display_Handler());
        frame.add(imageDisplay, BorderLayout.CENTER);

        JButton button1 = new JButton("Reset");
        button1.addActionListener(new button1_Handler());

        JComboBox ComboBox = new JComboBox(fractals);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Fractal:");
        panel.add(label);
        panel.add(ComboBox);
        frame.add(panel, BorderLayout.NORTH);
        ComboBox.addActionListener(new ComboBoxHandler());

        JPanel panel_b = new JPanel();
        panel_b.add(button1);
        JButton button2 = new JButton("«Save Image");
        panel_b.add(button2);
        frame.add(panel_b, BorderLayout.SOUTH);
        button2.addActionListener(new button2_Handler());



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
    public class button1_Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fractalGenerator.getInitialRange(rectangle);
            drawFractal();
        }
    }
    public class display_Handler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            double x = FractalGenerator.getCoord(rectangle.x, rectangle.x + rectangle.width, size, e.getX());
            double y = FractalGenerator.getCoord(rectangle.y, rectangle.y + rectangle.width, size, e.getY());
            fractalGenerator.recenterAndZoomRange(rectangle, x, y, 0.5);
            drawFractal();
        }
    }
    //В случае, если событие поступило от выпадающего
    //списка, извлекается выбранный элемент из виджета и устанавливается в
    //качестве текущего генератора фракталов.
    public class ComboBoxHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JComboBox combo = (JComboBox) e.getSource(); // возвращает ссылку на объект, от которого пришло событие
            String name = (String) combo.getSelectedItem();// возвращает элемент, который выбран.
            if (name == "Mandelbrot") {
                fractalGenerator = new Mandelbrot();
            }
            if (name == "Tricorn") {
                fractalGenerator = new Tricorn();
            }
            if (name == "BurningShip") {
                fractalGenerator = new BurningShip();
            }
            fractalGenerator.getInitialRange(rectangle); //сброс начального диапазона
            drawFractal(); //перерисовка фрактала
        }
    }
    /* класс javax.swing.JFileChooser реализует  возможность указания в какой файл будет сохранено изображение
Указанный класс предоставляет метод showSaveDialog(), который открывает
диалоговое окно «Save file», позволяя тем самым пользователю выбрать
директорию для сохранения. Метод принимает графический компонент,
 Если метод возвращает значение JfileChooser.APPROVE_OPTION, тогда можно продолжить операцию
сохранения файлов, в противном случае, пользователь отменил операцию,
поэтому закончите данную обработку события без сохранения.

write - метод класса ImageIO
//экземпляр BufferedImage из компонента JimageDisplay
Если пользователь выбрал директорию для сохранения файла, вы можете ее узнать,
используя метод getSelectedFile(), который возвращает объект типа File.

настройка средства выбора файлов, чтобы сохранять изображения только в формате PNGс помощью javax.swing.filechooser.FileNameExtensionFilter
Последняя строка гарантирует, что средство выбора не разрешит пользователю использование отличных от png форматов
     */
    class button2_Handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser file_chooser = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
            file_chooser.setFileFilter(filter);
            file_chooser.setAcceptAllFileFilterUsed(false);

            int result = file_chooser.showSaveDialog(imageDisplay); //результат операции выбора файла
            if(result==JFileChooser.APPROVE_OPTION) {
                try {
                    ImageIO.write(imageDisplay.image, "png",file_chooser.getSelectedFile());
                }catch(Exception exception) {
                    JOptionPane.showMessageDialog(file_chooser, exception.getMessage(),"Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }
    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(800);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }
}
