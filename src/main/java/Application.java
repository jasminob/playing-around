import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Application {

    public static List<Integer> IzdvojiSimetricneTernarne(List<Integer> vektorCijelih, Boolean val) {

        List<Integer> newList = new ArrayList<>();
        Boolean checker = false;


        if (val) {

            for (int j = 0; j < vektorCijelih.size(); j++) {
                for (int i = 2; i <= Math.abs(vektorCijelih.get(j)) / 2; ++i) {
                    if (vektorCijelih.get(j) % i == 0) {
                        checker = true;
                    }
                }
                if (!checker) {

                    if (!newList.contains(vektorCijelih.get(j)) && Palindrome(Ternary(vektorCijelih.get(j)))) {

                            newList.add(vektorCijelih.get(j));
                    }

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

                    if (!newList.contains(vektorCijelih.get(j)) && Palindrome(Ternary(vektorCijelih.get(j)))) {

                            newList.add(vektorCijelih.get(j));
                    }


                }
                checker = false;
            }

        }


        return newList;
    }

    public static int Ternary(int input) {
        int result = 0, factor = 1;
        boolean positive = true;

        if (input < 0) {
            input = Math.abs(input);
            positive = false;
        }

        while (input > 0) {
            result += input % 3 * factor;
            input = input / 3;
            factor = factor * 10;
        }

        if (positive) {
            return result;
        } else {
            return result * -1;
        }

    }

    public static boolean Palindrome(int input) {
        int reversedInt = 0, rem, oldInt;
        input = Math.abs(input);
        oldInt = Math.abs(input);

        for (; input != 0; input /= 10) {
            rem = input % 10;
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
        x.add(4);
        x.add(5);
        x.add(6);
        x.add(13);
        x.add(13);
        x.add(2);
        x.add(8);
        x.add(-454);
        x.add(-18);


        System.out.println(IzdvojiSimetricneTernarne(x, false));

    }
}


