package 백준.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj2096_내려가기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        int arr[][] = new int[len][3];
        for (int i = 0; i < len; i++) {
            arr[i] = strToIntArr(br.readLine());
        }
        int minArr[][] = new int[len][3];
        int maxArr[][] = new int[len][3];
        minArr[0] = arr[0];
        maxArr[0] = arr[0];

        for (int i = 1; i < len; i++) {
            minArr[i][0] += arr[i][0] + Math.min(minArr[i - 1][0], minArr[i - 1][1]);
            minArr[i][1] += arr[i][1] + Arrays.stream(minArr[i - 1]).min().getAsInt();
            minArr[i][2] += arr[i][2] + Math.min(minArr[i - 1][1], minArr[i - 1][2]);
            maxArr[i][0] += arr[i][0] + Math.max(maxArr[i - 1][0], maxArr[i - 1][1]);
            maxArr[i][1] += arr[i][1] + Arrays.stream(maxArr[i - 1]).max().getAsInt();
            maxArr[i][2] += arr[i][2] + Math.max(maxArr[i - 1][1], maxArr[i - 1][2]);
        }
        System.out.println(Arrays.stream(maxArr[len - 1]).max().getAsInt() + " " + Arrays.stream(minArr[len - 1]).min().getAsInt());
    }

    private static int[] strToIntArr(String str) {
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
