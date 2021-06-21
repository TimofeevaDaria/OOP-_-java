
import javafx.scene.control.ComboBox;

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
    private int rows_remaining ;
    private JComboBox ComboBox;
    private JButton button1;
    private JButton button2;

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

        button1 = new JButton("Reset");
        button1.addActionListener(new button1_Handler());

         ComboBox = new JComboBox(fractals);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Fractal:");
        panel.add(label);
        panel.add(ComboBox);
        frame.add(panel, BorderLayout.NORTH);
        ComboBox.addActionListener(new ComboBoxHandler());

        JPanel panel_b = new JPanel();
        panel_b.add(button1);
        button2 = new JButton("«Save Image");
        panel_b.add(button2);
        frame.add(panel_b, BorderLayout.SOUTH);
        button2.addActionListener(new button2_Handler());



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
    private void drawFractal() {
        enableUI(false);
        rows_remaining = size;

        for (int x=0; x<size; x++){
            FractalWorker drawRow = new FractalWorker(x);
            drawRow.execute();
        }
    }
    private void enableUI(boolean bool){
        ComboBox.setEnabled(bool);
        button1.setEnabled(bool);
        button2.setEnabled(bool);
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
            if (rows_remaining != 0) {
                return;
            }
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
    private class FractalWorker extends SwingWorker<Object, Object>{
        int Ycoord; //целочисленная координата вычисляемой строки
        int[] RGBvalues; // массив чисел типа int для хранения вычисленных значений RGB для каждого пикселя в этой строке
        private FractalWorker(int row) { //Конструктор должен будет получать y-координату в качестве параметра и сохранять это.
            Ycoord = row;
        }
        //метод, который выполняет фоновые операции
        //Этот метод вызывается в фоновом потоке. Он вычисляет
        //          * значение RGB для всех пикселей в 1 строке и сохраняет его в
        //          * соответствующий элемент целочисленного массива. Возвращает null.
        protected Object doInBackground() {
            RGBvalues = new int[size]; //память для массива целых чисел , в котором строки значений цвета)
            for (int i = 0; i < RGBvalues.length; i++) {
                double xCoord = FractalGenerator.getCoord(rectangle.x, rectangle.x + rectangle.width, size, i);
                double yCoord = FractalGenerator.getCoord(rectangle.y, rectangle.y + rectangle.width, size, Ycoord);
                int count = fractalGenerator.numIterations(xCoord, yCoord);
                if (count == -1) {
                    RGBvalues[i] = 0;
                }
                else{
                    float hue = 0.7f + (float) count / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    RGBvalues[i] = rgbColor;
                }

            }
            return null;
        }
        protected void done() {
            for (int i = 0; i < RGBvalues.length; i++) {
                imageDisplay.drawPixel(i, Ycoord, RGBvalues[i]);
            }
            imageDisplay.repaint(0, 0, Ycoord, size, 1);
            rows_remaining--;
            if (rows_remaining == 0) {
                enableUI(true);
            }

        }
    }

    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(800);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }
}
