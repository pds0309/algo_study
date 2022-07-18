package 백준.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj16571_알파틱택토 {
    private static final int SECOND_WIN = -1;
    private static final int SECOND_NONE = 2;
    private static final int SECOND_LOSE = 1;
    private static final int DRAW = 0;
    private static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[3][3];
        int sumOfZero = 0;
        for (int i = 0; i < 3; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sumOfZero += Arrays.stream(board[i]).filter(value -> value == 0).count();
        }
        Player_16571 firstPlayer = sumOfZero % 2 == 0 ? Player_16571.XRAY : Player_16571.ORANGE;
        int result = tracking(board, firstPlayer);
        if (result == SECOND_LOSE) {
            System.out.println('W');
            return;
        }
        if (result == DRAW) {
            System.out.println('D');
            return;
        }
        System.out.println('L');
    }

    private static int tracking(int[][] board, Player_16571 player) {
        int secondPlayerStatus = SECOND_NONE;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = player.getNumber();
                    if (isFinished(player.getNumber(), board)) {
                        secondPlayerStatus = Math.min(secondPlayerStatus, SECOND_WIN);
                    }
                    secondPlayerStatus = Math.min(secondPlayerStatus, tracking(board, player.changePlayer()));
                    board[i][j] = 0;
                }
            }
        }
        count++;
        if(count == 1956) {
            System.out.println("HI");
        }
        if (secondPlayerStatus == 2 || secondPlayerStatus == 0) {
            return DRAW;
        }
        return -secondPlayerStatus;
    }

    private static boolean isFinished(int currentPlayerNumber, int[][] board) {
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == currentPlayerNumber) {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] == currentPlayerNumber) {
            return true;
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] == currentPlayerNumber) {
                return true;
            }
        }
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] == currentPlayerNumber) {
                return true;
            }
        }
        return false;
    }

}

enum Player_16571 {
    ORANGE(1), XRAY(2);

    private final int number;

    Player_16571(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Player_16571 changePlayer() {
        if (this == ORANGE) {
            return XRAY;
        }
        return ORANGE;
    }
}