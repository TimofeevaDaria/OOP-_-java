package pack2;
import java.util.Scanner;

public class Lab2{
    public static void main(String[] args){
        Scanner scanner  = new Scanner(System.in);
        System.out.println("Координаты точки_1");
        double x1 = scanner .nextInt();
        double y1 =scanner .nextInt();
        double z1 =scanner .nextInt();
        System.out.println("Координаты точки_2");
        double x2 =scanner .nextInt();
        double y2 =scanner .nextInt();
        double z2 =scanner .nextInt();
        System.out.println("Координаты точки_3");
        double x3 =scanner .nextInt();
        double y3 =scanner .nextInt();
        double z3 =scanner .nextInt();
        scanner.close();

        Point3d point_1= new Point3d(x1, y1, z1);
        Point3d point_2= new Point3d(x2, y2, z2);
        Point3d point_3= new Point3d(x3, y3, z3);
       if (EqualityOfPoints(point_1, point_2, point_3 )) {
            System.out.println("Some points are identical, the triangle doesn't exist");
        }
       else {
           System.out.println("Площадь треугольника = " + computeArea(point_1, point_2, point_3) );
       }
    }
    public static double computeArea(Point3d point3d_1, Point3d point3d_2, Point3d point3d_3){
        double a1 = point3d_1.distanceTo(point3d_2); //Вычисление стороны a
        double a2 = point3d_2.distanceTo(point3d_3); //Вычисление стороны b
        double a3 = point3d_3.distanceTo(point3d_1); //Вычисление стороны c
        double p = ((a1+a2+a3)/2); //Вычисление полупериметра
        return Math.round(Math.sqrt(p*(p-a1)*(p-a2)*(p-a3))*100)/100D;
    }
    public static boolean EqualityOfPoints(Point3d point1, Point3d point2, Point3d point3) //Проверка на равенство точек
    {
        return
        (((point1.getX() == point2.getX()) && (point1.getY() == point2.getY()) && (point1.getZ() == point2.getZ())) ||
                ((point2.getX() == point3.getX()) && (point2.getY() == point3.getY()) && (point2.getZ() == point3.getZ())) ||
                ((point1.getX() == point3.getX()) && (point1.getY() == point3.getY()) && (point1.getZ() == point3.getZ())));
    }
}
