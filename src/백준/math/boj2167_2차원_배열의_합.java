package 백준.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj2167_2차원_배열의_합 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] xy = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] arr = new int[xy[0] + 1][xy[1] + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = Arrays.stream(("0 " + bufferedReader.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                arr[i][j] += (arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1]);
            }
        }
        int caseNum = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < caseNum; i++) {
            int[] cases = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x1 = cases[0];
            int y1 = cases[1];
            int x2 = cases[2];
            int y2 = cases[3];
            int ans = arr[x2][y2] + arr[x1 - 1][y1 - 1] - arr[x1 - 1][y2] - arr[x2][y1 - 1];
            stringBuilder.append(ans).append("\n");
        }
        System.out.println(stringBuilder);
    }
}
