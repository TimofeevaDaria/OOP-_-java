public class task1 {
    public static void main(String[] args) {
        System.out.println(convert(5));
        System.out.println(points(13,12));
        System.out.println(footballPoints(3,4,2));
        System.out.println(divisibleByFive(-55));
        System.out.println(and(true,false));
        System.out.println(howManyWalls(54,1,43));
        System.out.println(squared(9));
        System.out.println(profitableGamble(0.2, 50, 9));
        System.out.println(frames(10,25));
        System.out.println(mod(5,2));
    }
    //1.1
    public static int convert(int minutes) {

        return minutes*60;
    }
    //1.2
    public static int points(int two_point , int three_point ) {

        return two_point*2+three_point*3;
    }
    //1.3
    public static int footballPoints(int wins, int draws, int losses) {
        return wins*3+draws;
    }
    //1.4
    public static boolean divisibleByFive(int number) {

        return number%5==0;
    }
    //1.5
    public static boolean and(boolean a, boolean b) {

        return a&&b;
    }
    //1.6
    public static int howManyWalls(int n, int h, int w) {

        return n/(h*w);
    }
    //1.7
    public static int squared(int number) {

        return number * number;
    }
    //1.8
    public static boolean profitableGamble(double prob, double prize, double pay) {

        return prob*prize>pay;
    }
    //1.9
    public static int frames(int minutes, int fps) {

        return minutes*fps*60;
    }
    //1.10
    public static int mod(int a,int b) {

        return a-a/b*b;
    }

}
