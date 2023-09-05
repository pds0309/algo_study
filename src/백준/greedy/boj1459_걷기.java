package 백준.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1459_걷기 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        long[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long x = infos[0];
        long y = infos[1];
        long toStraight = infos[2];
        long toDiag = infos[3];
        if (toStraight * 2 < toDiag) {
            System.out.println((x + y) * toStraight);
            return;
        }
        long minVal = Math.min(x, y);
        x -= minVal;
        y -= minVal;
        long answer = minVal * toDiag;
        if ((x + y) % 2 == 1) {
            if (x > y) {
                x--;
            } else {
                y--;
            }
            answer += toStraight;
        }
        long straightDist = Math.abs(x - y);
        answer += straightDist * Math.min(toStraight, toDiag);
        System.out.println(answer);
    }
}
