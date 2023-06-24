package 백준.math;

import java.util.Scanner;

public class boj1699_제곱수의_합 {
    public static void main(String[] args) {
        int value = Integer.parseInt(new Scanner(System.in).nextLine());
        if (value <= 2) {
            System.out.println(value);
            return;
        }
        int[] memoArray = new int[value + 1];
        memoArray[1] = 1;
        memoArray[2] = 2;
        for (int i = 3; i <= value; i++) {
            if (Math.pow(getSqrtIntNum(i), 2) == i) {
                memoArray[i] = 1;
                continue;
            }
            int count = 100001;
            for (int j = 1; j * j <= i; j++) {
                count = Math.min(count, memoArray[i - j * j] + 1);
            }
            memoArray[i] = count;
        }
        System.out.println(memoArray[value]);
    }

    static int getSqrtIntNum(int value) {
        return (int) Math.floor(Math.sqrt(value));
    }

}
