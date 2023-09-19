package 백준.backtracking;

import java.util.Arrays;
import java.util.Scanner;

public class boj1074_Z {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] infos = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int size = infos[0];
        int x = infos[1];
        int y = infos[2];
        int ans = 0;
        while (size != 0) {
            int curVal = (int) Math.pow(4, size - 1);
            int curDim = (int) Math.pow(2, size - 1);
            if (x >= curDim) {
                x -= curDim;
                ans += curVal * 2;
            }
            if (y >= curDim) {
                y -= curDim;
                ans += curVal;
            }
            size--;
        }
        System.out.println(ans);
    }
}
