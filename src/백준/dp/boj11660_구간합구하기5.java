package 백준.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj11660_구간합구하기5 {

    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infoArray = getIntFromReader(bufferedReader);
        int[][] board = initBoard(infoArray[0], bufferedReader);
        long[][] cumulated = getCumulation(board);
        Command[] commands = initCommands(infoArray[1], bufferedReader);
        for (Command command : commands) {
            sb.append(command.getCumulatedValue(cumulated)).append("\n");
        }
        System.out.println(sb);
    }

    private static int[][] initBoard(int n, BufferedReader bufferedReader) throws IOException {
        int[][] array = new int[n][n];
        for (int i = 0; i < n; i++) {
            array[i] = getIntFromReader(bufferedReader);
        }
        return array;
    }

    private static Command[] initCommands(int m, BufferedReader bufferedReader) throws IOException {
        Command[] commands = new Command[m];
        for (int i = 0; i < m; i++) {
            commands[i] = new Command(getIntFromReader(bufferedReader));
        }
        return commands;
    }

    private static int[] getIntFromReader(BufferedReader bufferedReader) throws IOException {
        return Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static long[][] getCumulation(int[][] board) {
        long[][] sliced = new long[board.length + 1][board[0].length + 1];
        for (int i = 1; i < sliced.length; i++) {
            for (int j = 1; j < sliced[0].length; j++) {
                sliced[i][j] = sliced[i - 1][j] + sliced[i][j - 1] - sliced[i - 1][j - 1] + board[i - 1][j - 1];
            }
        }
        return sliced;
    }

    private static class Command {
        int x1;
        int y1;
        int x2;
        int y2;

        public Command(int[] array) {
            this.x1 = array[0] - 1;
            this.y1 = array[1] - 1;
            this.x2 = array[2] - 1;
            this.y2 = array[3] - 1;
        }

        public long getCumulatedValue(long[][] board) {
            return board[x1][y1] - board[x2 + 1][y1] - board[x1][y2 + 1] + board[x2 + 1][y2 + 1];
        }
    }
}
