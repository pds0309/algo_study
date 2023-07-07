package 백준.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj14728_벼락치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int caseNum = infos[0];
        int maxTime = infos[1];
        Study[] studies = new Study[caseNum + 1];
        for (int i = 1; i < studies.length; i++) {
            int[] studyInfo = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            studies[i] = new Study(studyInfo[0], studyInfo[1]);
        }
        int[][] dp = new int[maxTime + 1][caseNum + 1];
        for (int i = 1; i <= maxTime; i++) {
            for (int j = 1; j <= caseNum; j++) {
                if (i < studies[j].time) {
                    dp[i][j] = dp[i][j - 1];
                    continue;
                }
                int restIdx = Math.max(0, i - studies[j].time);
                int prevValue = dp[i][j - 1];
                dp[i][j] = Math.max(prevValue, dp[restIdx][j - 1] + studies[j].score);
            }
        }
        System.out.println(dp[maxTime][caseNum]);
    }

    static class Study {
        int time;
        int score;

        public Study(int time, int score) {
            this.time = time;
            this.score = score;
        }
    }
}
