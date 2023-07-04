package 백준.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj9084_동전 {
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < testCaseNum; i++) {
            bufferedReader.readLine();
            int[] coinArr = Arrays.stream(("0 " + bufferedReader.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
            int amount = Integer.parseInt(bufferedReader.readLine());
            findCase(coinArr, amount);
        }
        System.out.println(stringBuilder);
    }

    static void findCase(int[] coinArr, int requestedAmount) {
        int[][] dp = new int[requestedAmount + 1][coinArr.length];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                int currentCoinAmount = coinArr[j];
                int prevIndex = i - currentCoinAmount;
                if (prevIndex == 0) {
                    dp[i][j]++;
                } else if (prevIndex > 0) {
                    dp[i][j] = dp[i - currentCoinAmount][j];
                }
                dp[i][j] += dp[i][j - 1];
            }
        }
        stringBuilder.append(dp[requestedAmount][coinArr.length - 1]).append("\n");
    }
}
