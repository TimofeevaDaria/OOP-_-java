import java.util.Scanner;
public class Palindrome {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        args = new String[k];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i<k; i++){
            args[i] = scan.nextLine();;
        }
        for (int i=0; i<args.length; i++){
            if (isPalindrome(args[i])){
                System.out.println(args[i].toString()+ " is palindrome");
            }
            else {
                System.out.println(args[i].toString()+ " isn't palindrome");
            }
        }
    }
    public static String reverseString(String s){
        String s1 = "";
        for (int i=0;i<s.length();i++) {
            s1= s.charAt(i)+s1;
        }
        return s1;
    }
    public static boolean isPalindrome(String s){

        return reverseString(s).equals(s);
    }
    
    
    
}
