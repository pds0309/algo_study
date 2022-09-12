package programmers.lv2;

import java.util.Arrays;

public class 카카오프렌즈컬러링 {
    static int[] X = {0, 1, -1, 0};
    static int[] Y = {1, 0, 0, -1};

    public static void main(String[] args) {
        int[][] picture =
                {
                        {1, 1, 1, 0},
                        {1, 2, 2, 0},
                        {1, 0, 0, 1},
                        {0, 0, 0, 1},
                        {0, 0, 0, 3},
                        {0, 0, 0, 3}};
        int m = 6;
        int n = 4;
        System.out.println(Arrays.toString(solution(m, n, picture)));
    }

    static int[] answer = {0, -1};

    public static int[] solution(int m, int n, int[][] picture) {
        int[][] pics = new int[m + 2][n + 2];
        for (int i = 1; i < pics.length - 1; i++) {
            for (int j = 1; j < pics[i].length - 1; j++) {
                pics[i][j] = picture[i - 1][j - 1];
            }
        }
        for (int i = 1; i < pics.length - 1; i++) {
            for (int j = 1; j < pics[i].length - 1; j++) {
                if (pics[i][j] == 0) {
                    continue;
                }
                answer[1] = Math.max(answer[1], dfs(pics, i, j, pics[i][j], 0));
                answer[0]++;
            }
        }
        int[] ans = answer.clone();
        answer = new int[]{0, -1};
        return ans;
    }

    private static int dfs(int[][] pics, int x, int y, int value, int count) {
        if (pics[x][y] == value) {
            count++;
            pics[x][y] = 0;
            for (int i = 0; i < X.length; i++) {
                count = dfs(pics, x + X[i], y + Y[i], value, count);
            }
        }
        return count;
    }
}
