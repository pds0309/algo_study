package 백준;

import java.util.Arrays;
import java.util.Scanner;

public class boj1476_날짜_계산 {
    public static void main(String[] args) {
        int[] arr = Arrays.stream(new Scanner(System.in).nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = arr[0];
        int y = arr[1];
        int z = arr[2];
        int cnt = x;
        int tempY = x;
        int tempZ = x;
        while (true) {
            if (tempY == y && tempZ == z) {
                System.out.println(cnt);
                return;
            }
            tempY = getVal(tempY + 15, 28);
            tempZ = getVal(tempZ + 15, 19);
            cnt += 15;
        }
    }

    static int getVal(int v, int max) {
        if (v == max) {
            return v;
        }
        return v % max;
    }
}
