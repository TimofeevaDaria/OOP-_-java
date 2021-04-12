package pack2;

public final class Point3d extends Point2d{
    private double zCoord;
    public Point3d(double x, double y, double z) { //Конструктор инициализации
        super(x,y);
        zCoord=z;
    }
    public Point3d() { //Конструктор по умолчанию
        super(0,0);
        zCoord=0;
    }
    public double getZ() { //Возвращение координаты Z
        return zCoord;
    }
    public void setZ(double z) { //Установка значения координаты Z
        zCoord=z;
    }
    public boolean Comparison (Point3d point){ //метод для сравнения  двух объектов Point3d
        return (
                this.getX() == point.getX() && this.getY() == point.getY() && this.getZ() == point.getZ());
    }
    public double distanceTo(Point3d point){ //в качестве параметра принимает другой объект Point3d, вычисляет расстояние между двумя точками
        if(!this.Comparison (point)){
            double X = Math.pow(this.getX() - point.getX(), 2.0);
            double Y = Math.pow(this.getY() - point.getY(), 2.0);
            double Z = Math.pow(this.getZ() - point.getZ(), 2.0);
            return Math.sqrt(X + Y + Z);
        }
        else
            return 0;
    }
}
