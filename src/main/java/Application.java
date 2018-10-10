import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Application {

    public static List<Integer> IzdvojiSimetricneTernarne(List<Integer> vektorCijelih, Boolean val) {

        List<Integer> newList = new ArrayList<>();
        Boolean checker = false;


        List<Integer> ternaryLoop = new ArrayList<>();
        List<Integer> result = new ArrayList<>();


        if (val) {

            for (int j = 0; j < vektorCijelih.size(); j++) {
                for (int i = 2; i <= Math.abs(vektorCijelih.get(j)) / 2; ++i) {
                    if (vektorCijelih.get(j) % i == 0) {
                        checker = true;
                    }
                }
                if (!checker) {
                    newList.add(vektorCijelih.get(j));
                    System.out.println(Palindrome(vektorCijelih.get(j)));

                }
                checker = false;
            }
        } else {
            for (int j = 0; j < vektorCijelih.size(); j++) {
                for (int i = 2; i <= Math.abs(vektorCijelih.get(j)) / 2; ++i) {
                    if (vektorCijelih.get(j) % i == 0) {
                        checker = true;
                    }
                }
                if (checker) {
                    newList.add(vektorCijelih.get(j));
                    System.out.println(Palindrome(vektorCijelih.get(j)));

                }
                checker = false;
            }

        }


       /* int c = 0;
       int factor = 1;

        for(int l = 0; l<newList.size(); l++) {
            int b = newList.get(l);


           while(b > 0) {

                c += b % 3 * factor;
                b = b / 3;
                factor = factor * 10;


            }

            result.add(c);

        }
*/


        return newList;
    }

    public static long Ternary(int input) {
        long result = 0, factor = 1;
        while (input > 0) {
            result += input % 3 * factor;
            input = input / 3;
            factor = factor * 10;
        }
        return result;
    }

    public static boolean Palindrome(int input) {
        int reversedInt = 0, rem, oldInt, newInt;
        newInt = Math.abs(input);
        oldInt = Math.abs(input);

        for (; newInt != 0; newInt /= 10) {
            rem = newInt % 10;
            reversedInt = reversedInt * 10 + rem;
        }

        if (oldInt == reversedInt) {

            return true;

        } else {
            return false;
        }

    }


    public static void main(String[] args) {

        List<Integer> x = new ArrayList<>();

        x.add(2);
        x.add(3);
        x.add(4);
        x.add(5);
        x.add(6);
        x.add(7);
        x.add(8);
        x.add(-454);
        x.add(-18);


        System.out.println(IzdvojiSimetricneTernarne(x, false));

    }
}


