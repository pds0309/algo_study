package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class boj16500_문자열_판별 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String S = bufferedReader.readLine();
        int len = Integer.parseInt(bufferedReader.readLine());
        int[] dp = new int[S.length() + 1];
        Set<String> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            set.add(bufferedReader.readLine());
        }
        for (int i = S.length() - 1; i >= 0; i--) {
            if (set.contains(S.substring(i))) {
                dp[i] = 1;
            }
            for (int j = i + 1; j < S.length(); j++) {
                if (dp[j] == 1 && set.contains(S.substring(i, j))) {
                    dp[i] = 1;
                    break;
                }
            }
        }
        System.out.println(dp[0]);
    }
}
