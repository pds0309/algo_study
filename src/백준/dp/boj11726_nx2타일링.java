package 백준.dp;

import java.util.Scanner;

public class boj11726_nx2타일링 {
    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        int[] array = new int[n + 2];
        array[0] = 0;
        array[1] = 1;
        array[2] = 1;
        for (int i = 2; i <= n + 1; i++) {
            array[i] = (array[i - 1] + array[i - 2]) % 10007;
        }
        System.out.println(array[n + 1]);
    }
}
