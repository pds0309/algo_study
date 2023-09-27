package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj14719_빗물 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int height = infos[0];
        int width = infos[1];
        int[] arr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Dim start = new Dim(0, arr[0]);
        Dim end = new Dim(arr.length - 1, arr[arr.length - 1]);
        int min = 0;
        int ans = 0;
        while (start.index < end.index) {
            if (start.num > end.num) {
                min = end.num;
                end = new Dim(end.index - 1, Math.max(min, arr[end.index - 1]));
                ans += end.num - arr[end.index];
                continue;
            }
            min = start.num;
            start = new Dim(start.index + 1, Math.max(min, arr[start.index + 1]));
            ans += start.num - arr[start.index];
        }
        System.out.println(ans);
    }

    static class Dim {
        int index;
        int num;

        public Dim(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }
}
