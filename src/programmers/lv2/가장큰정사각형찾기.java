package programmers.lv2;

import java.util.Arrays;

public class 가장큰정사각형찾기 {
    public static void main(String[] args) {
        int[][] board = {{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 0}};
        System.out.println(solution(board));
    }

    public static int solution(int[][] board) {
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                if (board[i - 1][j] == 0 || board[i][j - 1] == 0 || board[i - 1][j - 1] == 0) {
                    continue;
                }
                board[i][j] = Math.min(Math.min(board[i - 1][j], board[i][j - 1]) + 1, board[i - 1][j - 1] + 1);
            }
        }
        return (int) Math.pow(Arrays.stream(board)
                .flatMapToInt(Arrays::stream)
                .max()
                .orElse(0), 2);
    }
}
