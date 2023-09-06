package 백준.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj11052_카드_구매하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bufferedReader.readLine());
        int[] arr = Arrays.stream(("0 " + bufferedReader.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[len + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i - j < 0) {
                    break;
                }
                dp[i] = Math.max(dp[i], dp[i - j] + arr[j]);
            }
        }
        System.out.println(dp[dp.length - 1]);
    }
}
