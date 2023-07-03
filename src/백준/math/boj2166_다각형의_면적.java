package 백준.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj2166_다각형의_면적 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(bufferedReader.readLine());
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            points.add(Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }
        points.add(points.get(0));
        long rel1 = 0;
        long rel2 = 0;
        for (int i = 0; i < count; i++) {
            int[] current = points.get(i);
            int[] next = points.get(i + 1);
            rel1 += (long) current[0] * next[1];
            rel2 += (long) current[1] * next[0];
        }
        double result = Math.round(((double) rel1 - rel2) / 2 * 10) / 10.0;
        String formattedNumber = String.format("%.1f", Math.abs(result));
        System.out.println(formattedNumber);
    }
}
