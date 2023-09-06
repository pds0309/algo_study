package 백준.dp;

import java.util.Scanner;

public class boj11057_오르막_수 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        if (n == 1) {
            System.out.println(10);
            return;
        }
        long[] dp = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        for (int i = 2; i <= n + 1; i++) {
            for (int j = 1; j < 10; j++) {
                dp[j] += dp[j - 1];
                dp[j] %= 10007;
            }
        }
        System.out.println(dp[dp.length - 1] % 10007);
    }
}
