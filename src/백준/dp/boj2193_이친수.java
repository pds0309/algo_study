package 백준.dp;

import java.util.Scanner;

public class boj2193_이친수 {
    public static void main(String[] args) {
        int num = Integer.parseInt(new Scanner(System.in).nextLine());
        long[] arr = new long[91];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        if (num <= 2) {
            System.out.println(1);
            return;
        }
        for (int i = 3; i <= num; i++) {
            arr[i] = arr[i - 2] + arr[i - 1];
        }
        System.out.println(arr[num] - arr[num - 2]);
    }
}
