import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator {
    @Override
    public void getInitialRange(Rectangle2D.Double rectangle) {
        rectangle.x = -2.5;
        rectangle.y = -2;
        rectangle.height = 4;
        rectangle.width = 4;
    }
    public static final int MAX_ITERATIONS = 2000;

    //абсолютное значение каждого компонента Zn-1 на каждой итерации
    @Override
    public int numIterations(double x, double y) {
        double real = x;
        double imaginary = y;
        int count = 0;
        while (count < MAX_ITERATIONS) {
            count++;
            double k = real * real - imaginary*imaginary+x;
            double m = 2 * Math.abs(real)*Math.abs(imaginary)+y;
            real = k;
            imaginary = m;
            if (real*real+imaginary*imaginary > 4)
                break;
        }

        if (count == MAX_ITERATIONS)
            return -1;
        return count;
    }
}