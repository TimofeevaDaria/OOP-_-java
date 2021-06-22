import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;
import java.lang.Object;


public class task4 {
    public static void main(String[] args) {
        int[] ar1 = {2, 55, 60, 97, 86};
        int[] ar2 = {5, 1, 4, 3, 2, 8};
        int[] ar3 = {9, 4, 26, 26, 0, 0, 5, 20, 6, 25, 5};
        System.out.println(sevenBoom(ar1)); //4.1
        System.out.println(cons(ar2));//4.2
        System.out.println(unmix("hTsii  s aimex dpus rtni.g"));//4.3
        System.out.println(noYelling("Oh my goodness!!!"));//4.4
        System.out.println(xPronounce("OMG x box unboxing video x D"));//4.5
        System.out.println(largestGap(ar3));//4.6
        System.out.println(fun(665));//4.7
        System.out.println(commonLastVowel("Watch the characters dance!"));//4.8
        System.out.println(memeSum(124, 98));
        System.out.println(unrepeated("hello"));
    }

    //4.1
    public static String sevenBoom(int[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            str = str + Integer.toString(arr[i]);
        }
        if (str.contains("7")) {
            return "Boom!";
        }
        return "There is no 7 in array";
    }

    //4.2
    public static boolean cons(int[] arr) {
        Arrays.sort(arr);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - 1 != arr[i - 1])
                return false;
        }
        return true;
    }

    //4.3
    public static String unmix(String str) {
        String res = "";
        for (int i = 0; i < str.length() / 2; i++) {
            res += str.charAt(i * 2 + 1);
            res += str.charAt(i * 2);
        }
        if (str.length() % 2 == 1) {
            res += str.charAt(str.length() - 1);
        }
        return res;
    }

    //4.4
    public static String noYelling(String str) {
        int i = str.length() - 1;
        while ((str.charAt(i) == '!' || str.charAt(i) == '?') && i != 0 && (str.charAt(i - 1) == '!' || str.charAt(i - 1) == '?')) {
            str = str.substring(0, i);
            i -= 1;
        }
        return str;
    }

    //4.5
    public static String xPronounce(String str) {
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'x') {
                if (i == 0 || (str.charAt(i - 1) == ' ' && str.charAt(i + 1) != ' ')) {
                    res = res + "z";
                    continue;
                } else if ((str.charAt(i - 1) == ' ' && str.charAt(i + 1) == ' ')) {
                    res = res + "ecks";
                    continue;
                } else {
                    res = res + "cks";
                    continue;
                }
            }
            res = res + str.charAt(i);
            continue;
        }
        return res;
    }

    //4.6
    public static int largestGap(int[] arr) {
        int res = 0;
        Arrays.sort(arr);
        for (int i = 1; i < arr.length; i++) {
            if (res < arr[i] - arr[i - 1])
                res = arr[i] - arr[i - 1];
        }
        return res;
    }

    //4.7 только решение без условия
    public static int fun(int a) {
        String str1 = "";
        String str2 = Integer.toString(a);
        for (int i = 0; i < str2.length(); i++) {
            str1 = str2.charAt(i) + str1;
        }
        int a1 = Integer.parseInt(str1);
        return a - a1;
    }

    //4.8
    public static String commonLastVowel(String str) {
        int o_count = 0;
        int e_count = 0;
        int y_count = 0;
        int u_count = 0;
        int i_count = 0;
        int a_count = 0;
        String result = "";
        str = str.toLowerCase();
        str = str + " ";
        for (int j = 0; j < str.length(); j++) {
            if (str.charAt(j) == ' ') {
                if (str.charAt(j - 1) == 'o') {
                    o_count += 1;
                }
                if (str.charAt(j - 1) == 'e') {
                    e_count += 1;
                }
                if (str.charAt(j - 1) == 'y') {
                    y_count += 1;
                }
                if (str.charAt(j - 1) == 'u') {
                    u_count += 1;
                }
                if (str.charAt(j - 1) == 'i') {
                    i_count += 1;
                }
                if (str.charAt(j - 1) == 'a') {
                    a_count += 1;
                }
            }
        }
        int[] arr = new int[]{o_count, e_count, y_count, u_count, i_count, a_count};
        int max = 0;
        int index = -1;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] >= max) {
                max = arr[j];
                index = j;
            }
        }
        switch (index) {
            case -1:
                result = "No vowels";
                break;
            case 0:
                result = "o";
                break;
            case 1:
                result = "e";
                break;
            case 2:
                result = "y";
                break;
            case 3:
                result = "u";
                break;
            case 4:
                result = "i";
                break;
            case 5:
                result = "a";
                break;
        }
        return result;
    }

    //4.9
    public static int memeSum(int a, int b) {
        String str = "";
        String a1 = Integer.toString(a);
        String b1 = Integer.toString(b);
        int len_a = a1.length();
        int len_b = b1.length();
        int maxlen = Math.max(len_a, len_b);
        for (int i = 0; i <= maxlen; i++) {
            str += Integer.toString(a / (int) Math.pow(10, maxlen - i) + (int) b / (int) Math.pow(10, maxlen - i));
            a %= (int) Math.pow(10, maxlen - i);
            b %= (int) Math.pow(10, maxlen - i);
        }
        return Integer.parseInt(str);
    }

    //4.10
    public static String unrepeated(String str) {
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            if (!res.contains(Character.toString(str.charAt(i)))) {
                res = res + str.charAt(i);
            }
        }
        return res;
    }
}
