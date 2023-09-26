package 백준.dp;

import java.util.Scanner;

public class boj12852_1로_만들기_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        int[] dp = new int[num + 1];
        int[] hist = new int[num + 1];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + 1;
            hist[i] = i - 1;
            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                hist[i] = i / 3;
            }
            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                hist[i] = i / 2;
            }
        }
        System.out.println(dp[dp.length - 1]);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(num).append(" ");
        while (num > 1) {
            stringBuilder.append(hist[num] + " ");
            num = hist[num];
        }
        System.out.println(stringBuilder);
    }
}
