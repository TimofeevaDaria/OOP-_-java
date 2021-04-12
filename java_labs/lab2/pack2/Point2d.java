package pack2;
public class Point2d {
    private double xCoord; // координата X
    private double yCoord; // координата Y
    public Point2d ( double x, double y) { //Конструктор инициализации
        xCoord = x;
        yCoord = y;
    }
    public Point2d () { //Конструктор по умолчанию
        this(0, 0);
    }
    public double getX () { //Возвращение координаты X

        return xCoord;
    }
    public double getY () { // Возвращение координаты Y

        return yCoord;
    }
    public void setX ( double val) { //Установка значения координаты X

        xCoord = val;
    }
    public void setY ( double val) { //Установка значения координаты Y

        yCoord = val;
    }
}
