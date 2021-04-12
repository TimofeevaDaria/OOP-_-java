public class task2 {
    public static void main(String[] args) {
        int[] mas = {10, 4, 1, 4, -10, -50, 32, 21};
        System.out.println(oppositeHouse(1,3)); //1
        System.out.println(nameShuffle("Daria Timofeeva"));  //2
        System.out.println((discount(1500,50))); //3
        System.out.println(differenceMaxMin(mas)); //4
        System.out.println(equal(3,4,3)); //5
        System.out.println(reverse("Hello World!")); //6
        System.out.println(programmers(1,7,9)); //7
        System.out.println(getXO("ooxhghv")); //8
        System.out.println(bomb("Hey, did you think there is a  Bomb?")); //9
        System.out.println(saveAscii("AA", "B@")); //10


    }
    //2.1
    public static int oppositeHouse(int Number, int Length ) {
        return Length*2+1 - Number;
    }
    //2.2
    public static String nameShuffle(String name) {
        return name.split(" ")[1] + " " + name.split(" ")[0];
    }
    //2.3
    public static double discount(double value, double procent) {
        return value*procent/100;
    }
    //2.4
    public static double differenceMaxMin(int[] ar) {
        int min=ar[0];
        int max=ar[0];
        for (int i = 0; i<ar.length; i++) {
            if (ar[i] > max)
                    max = ar[i];
            if (ar[i]<min)
                min = ar[i];
        }
        return max-min;
    }
    //2.5
    public static int equal(int a, int b, int c){
        int k;
        if ((a == b) && (a ==c)) {
            k = 3;
        }
        else if ((a!=b) && (a!=c) && (b!=c)){
            k = 0;
        }
        else{
            k = 2;
        }
        return k;
    }
    //2.6
    public static String reverse(String s){
        String s1 = "";
        for (int i=0;i<s.length();i++) {
            s1= s.charAt(i) + s1;
        }
        return s1;
    }
    //2.7
    public static double programmers(double a, double b, double c) {
        return Math.max(a, Math.max(b,c)) - Math.min(a, Math.min(b,c));
    }
    //2.8
    public static boolean getXO(String s){
        char ch1 = 'x';
        char ch2 = 'o';
        int k1 = 0; int k2 = 0;
        for (int i=0; i< s.length();i++){
            if (s.toLowerCase().charAt(i) == ch1) {
                k1 = k1 + 1;
            }
            if (s.toLowerCase().charAt(i) == ch2) {
                k2 = k2 + 1;
            }
        }
        return k1 == k2;
    }
    //2.9
    public static String bomb(String s){
        String message;
        String s1 = s.toLowerCase();
        int value = s1.indexOf("bomb");
        if (value == -1) {
            message = "Relax, there's no bomb.";
        }
        else{
            message = "DUCK!";
        }
        return message;
    }
    //2.10
    public static boolean saveAscii(String one, String two) {
        int ascii_1; int ascii_2;
        int sum1 = 0; int sum2 = 0;
        for (int i = 0; i < one.length(); i++){
            ascii_1 = (int)one.charAt(i);
            sum1 = sum1+ascii_1;
        }
        for (int i = 0; i < two.length(); i++){
            ascii_2 = (int)two.charAt(i);
            sum2 = sum2+ascii_2;
        }
        return sum1==sum2;
    }
}
