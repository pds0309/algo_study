package 백준.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj2294_동전2 {
    static int INF = 10001;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int cases = infos[0];
        int goal = infos[1];
        int[] coins = new int[cases];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(bufferedReader.readLine());
        }
        int[] dp = new int[goal + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            int currentCoin = coins[i];
            for (int j = currentCoin; j < dp.length; j++) {
                dp[j] = Math.min(dp[j], dp[j - currentCoin] + 1);
            }
        }
        System.out.println(dp[goal] == INF ? -1 : dp[goal]);
    }
}
