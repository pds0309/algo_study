package 백준.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj11758_CCW {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        long[] l1 = Arrays.stream(bufferedReader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] l2 = Arrays.stream(bufferedReader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] l3 = Arrays.stream(bufferedReader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long ccw = (l1[0] * l2[1] + l2[0] * l3[1] + l3[0] * l1[1]) - (l1[1] * l2[0] + l2[1] * l3[0] + l3[1] * l1[0]);
        System.out.println(Math.max(-1, Math.min(1, ccw)));
    }
}
