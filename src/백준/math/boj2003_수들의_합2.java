package 백준.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj2003_수들의_합2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int len = infos[0];
        int sum = infos[1];
        int[] arr = Arrays.stream((bufferedReader.readLine() + " 0").split(" ")).mapToInt(Integer::parseInt).toArray();
        int left = 0;
        int right = 0;
        int currentSum = 0;
        int answer = 0;
        for (int i = 0; i < len + 1; i++) {
            if (currentSum == sum) {
                answer++;
            }
            currentSum += arr[right++];
            while (currentSum > sum) {
                currentSum -= arr[left++];
            }
        }
        System.out.println(answer);
    }
}
