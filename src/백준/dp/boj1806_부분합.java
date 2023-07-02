package 백준.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1806_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int requestNum = Integer.parseInt(bufferedReader.readLine().split(" ")[1]);
        int[] arr = Arrays.stream((bufferedReader.readLine() + " 0").split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = 0;
        int end = 0;
        int answer = Integer.MAX_VALUE;
        int sum = arr[0];
        while (start <= end && end < arr.length - 1) {
            if (sum >= requestNum) {
                answer = Math.min(answer, end - start + 1);
                sum -= arr[start++];
                continue;
            }
            sum += arr[++end];
        }
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}
