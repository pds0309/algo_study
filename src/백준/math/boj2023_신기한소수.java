package 백준.math;

import java.util.Scanner;

public class boj2023_신기한소수 {
    static int[] firstPrimeArr = {2, 3, 5, 7};
    static int[] notFirstPrimeArr = {1, 3, 7, 9};

    public static void main(String[] args) {
        int number = Integer.parseInt(new Scanner(System.in).nextLine());
        for (int i = 0; i < 4; i++) {
            per(number, new StringBuilder().append(firstPrimeArr[i]));
        }
    }

    static void per(int maxCount, StringBuilder currentStr) {
        if (maxCount == currentStr.toString().length()) {
            System.out.println(currentStr);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int currentNumber = Integer.parseInt(currentStr.toString() + notFirstPrimeArr[i]);
            if (isPrime(currentNumber)) {
                per(maxCount, currentStr.append(notFirstPrimeArr[i]));
                currentStr.setLength(currentStr.length() - 1);
            }
        }
    }

    static boolean isPrime(int number) {
        if (number == 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
