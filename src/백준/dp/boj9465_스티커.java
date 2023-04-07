package 백준.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj9465_스티커 {
    private static final StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < length; i++) {
            int len = Integer.parseInt(bufferedReader.readLine());
            int[][] array = new int[2][len + 1];
            array[0] = strToIntArr("0 " + bufferedReader.readLine());
            array[1] = strToIntArr("0 " + bufferedReader.readLine());
            stringBuilder.append(findMaxValue(array)).append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static int findMaxValue(int[][] array) {
        for (int i = 2; i < array[0].length; i++) {
            array[0][i] += Math.max(array[1][i - 1], array[1][i - 2]);
            array[1][i] += Math.max(array[0][i - 1], array[0][i - 2]);
        }
        return Math.max(array[0][array[0].length - 1], array[1][array[0].length - 1]);
    }

    private static int[] strToIntArr(String str) {
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

}
