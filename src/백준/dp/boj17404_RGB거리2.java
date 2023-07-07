package 백준.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj17404_RGB거리2 {
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int caseNum = Integer.parseInt(bufferedReader.readLine());
        int[][] arr = new int[caseNum][3];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int color = 0; color < 3; color++) {
            int[][] dp = copy(arr);
            dp[0][color] = 100000001;
            for (int i = 1; i < dp.length; i++) {
                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        dp[i][j] += Math.min(dp[i - 1][j + 1], dp[i - 1][j + 2]);
                        continue;
                    }
                    if (j == 1) {
                        dp[i][j] += Math.min(dp[i - 1][j + 1], dp[i - 1][j - 1]);
                        continue;
                    }
                    dp[i][j] += Math.min(dp[i - 1][j - 2], dp[i - 1][j - 1]);
                }
            }
            answer = Math.min(dp[caseNum - 1][color], answer);
        }
        System.out.println(answer);
    }

    static int[][] copy(int[][] arr) {
        int[][] newArr = new int[arr.length][arr[0].length];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return newArr;
    }
}
