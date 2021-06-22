import java.util.Arrays;
public class task3 {
    public static void main(String[] args) {
        String[][] arr= {{"Manila","13923452"},{"Kuala Lumpur","7996830"},{"Jakarta","10770487"}};
        double[] sides = othersides(1);
        int[] mas = {5, 9, 45, 6, 2, 7, 34, 8, 6, 90, 5, 243};
        int[] mas1 = {1, 0, 4, 5, 2, 4, 1, 2, 3, 3, 3};
        System.out.println(Arrays.deepToString(millionsRounding(arr))); //3.1
        System.out.println(sides[0] + "  " + sides[1]); //3.2
        System.out.println(rps("rock","paper")); //3.3
        System.out.println(warOfNumbers(mas)); //3.4
        System.out.println(reverseCase("sPoNtAnEoUs"));//3.5
        System.out.println(inatorInator("EvilClone"));//3.6
        System.out.println(doesBrickFit(1, 2, 1, 1, 1));//3.7
        System.out.println(totalDistance(36.1, 8.6, 3, true));//3.8
        System.out.println(mean(mas1));//3.9
        System.out.println(parityAnalysis(234));
    }
    //3.1
    public static String[][] millionsRounding(String[][] ar) {
        for (int i=0; i<ar.length; i++){
            int ar1 = Integer.parseInt (ar[i][1]);
            int ar2 = (ar1/1000000)*1000000;
            ar[i][1] = Integer.toString(ar2);
        }
        return ar;
    }

    //3.2
    public static double[] othersides(double a) {
        double c = a*2;
        double b = ((Math.sqrt(c*c - a*a))*100)/100;
        double[] sides = new  double[] {Math.round(c*100)/100D, Math.round(b*100)/100D};
        return sides;
    }

    //3.3
    public static String rps(String player1,String player2){ //rock-камень, paper - бумага, scissors - ножницы
        if (player1.equals(player2))
            return "TIE";
        if ((player1.charAt(0)=='r' && player2.charAt(0)=='s')
                || (player1.charAt(0)=='p' && player2.charAt(0)=='r')
                || (player1.charAt(0)=='s' && player2.charAt(0)=='p'))
            return "Player1 win";
        else
            return "Player2 win";
    }

    //3.4
    public static int warOfNumbers(int[] mas){
        int even_sum = 0;
        int odd_sum = 0;
        for (int i = 0; i<mas.length; i++) {
            if (mas[i] % 2 == 0)
                even_sum = even_sum + mas[i];
            else
                odd_sum = odd_sum + mas[i];
        }
        return Math.abs(even_sum-odd_sum);
    }
    //3.5
    public static String reverseCase(String str){
        String str1 = "";
        for (int i=0;i<str.length();i++) {
            if(str.charAt(i)>=65 && str.charAt(i)<=90) {
                str1 = str1 + str.toLowerCase().charAt(i);
            }
            else if(str.charAt(i)>=97 && str.charAt(i)<=122) {
                str1 = str1 + str.toUpperCase().charAt(i);
            }
        }
        return str1;
    }
    //3.6
    public static String inatorInator(String str){
        int len = str.length();
        String vowels = "AaEeYyUuIiOo";
        char last = str.charAt(len-1);
        if( vowels.contains(Character.toString(last)) ) {
            str= str+"-inator";
        }else {
            str= str+"inator";
        }
        return str + " "+ len+"000" ;
    }
    //3.7
    public static boolean doesBrickFit(int a, int b,int c,int w, int h){
        return ((a<=w && b<=h) || (b<=w && a<=h) || (b<=w && c<=h) || (c<=w && b<=h) || (a<=w && c<=h) || (c<=w && a<=h));
    }
    //3.8
    public static double totalDistance(double litrs, double raskhod, int pass, boolean cond){
        raskhod = (pass*5+100)*raskhod/100;
        if (cond)
            raskhod = raskhod*1.1;
        return Math.round(litrs/(raskhod/100)*100)/100D;
    }
    //3.9
    public static double mean (int[] arr) {
        double result=0;
        for (int i=0; i<arr.length; i++){
            result +=arr[i];
        }
        return Math.round(result/arr.length*100)/100D;
    }
    //3.10
    public static boolean parityAnalysis(int a) {
        int a1 = a%2;
        int sum=0;
        while(a>0){
            sum+=a%10;
            a=a/10;
        }
        return (sum%2 == a1);
    }
}
