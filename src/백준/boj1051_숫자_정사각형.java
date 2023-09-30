package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1051_숫자_정사각형 {
    static int answer = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = infos[0];
        int y = infos[1];
        int[][] arr = new int[x][y];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Arrays.stream(bufferedReader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 0; i < x - 1; i++) {
            for (int j = 0; j < y - 1; j++) {
                int start = arr[i][j];
                int minBound = Math.min(x - i - 1, y - j - 1);
                int tempLen = 0;
                for (int k = 1; k <= minBound; k++) {
                    tempLen = isSquare(k, i, j, start, arr) ? k : tempLen;
                }
                answer = Math.max(answer, tempLen + 1);
            }
        }
        System.out.println(answer * answer);
    }

    static boolean isSquare(int k, int x, int y, int start, int[][] arr) {
        return (start == arr[x + k][y] && start == arr[x][y + k] && start == arr[x + k][y + k]);
    }
}
