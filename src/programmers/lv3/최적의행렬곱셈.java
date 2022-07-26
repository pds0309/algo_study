package programmers.lv3;

import java.util.Arrays;

public class 최적의행렬곱셈 {
    public static void main(String[] args) {
        int[][] matrix_sizes = {{5, 3}, {3, 10}, {10, 6}};
    }

    public static int solution(int[][] matrix_sizes) {
        int len = matrix_sizes.length;
        int[][] dp = new int[len][len];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        for (int i = 0; i < len; i++) {
            for (int start = 0; start < len - i; start++) {
                int end = i + start;
                if (start == end) {
                    dp[start][end] = 0;
                    continue;
                }
                for (int mid = start; mid < end; mid++) {
                    dp[start][end] = Math.min(dp[start][end],
                            dp[start][mid] + dp[mid + 1][end] +
                                    matrix_sizes[start][0] * matrix_sizes[mid][1] * matrix_sizes[end][1]
                    );
                }
            }
        }

        return dp[0][matrix_sizes.length - 1];
    }
}
