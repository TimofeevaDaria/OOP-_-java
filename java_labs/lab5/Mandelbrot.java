
import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {
    @Override
    public void getInitialRange(Rectangle2D.Double rectangle) {
        rectangle.x = -2;
        rectangle.y = -1.5;
        rectangle.height = 3;
        rectangle.width = 3;
    }
    public static final int MAX_ITERATIONS = 2000;

    @Override
    public int numIterations(double x, double y) {
        double real = x;
        double imaginary = y;
        int count = 0;
        while (count < MAX_ITERATIONS) {
            count++;
            double k = real * real - imaginary*imaginary+x;
            double m = 2 * real * imaginary + y;
            real = k;
            imaginary = m;

            if (real*real+imaginary*imaginary > 4) // |z|^2 > 2^2
                break;
        }

        if (count == MAX_ITERATIONS)
            return -1;
        return count;
    }
}