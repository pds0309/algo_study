package 백준.backtracking;

import java.util.Scanner;

public class boj9663_N_Queen {
    static int answer = 0;

    public static void main(String[] args) {
        int N = Integer.parseInt(new Scanner(System.in).nextLine());
        track(0, N, new int[N]);
        System.out.println(answer);
    }

    static void track(int x, int maxCnt, int[] array) {
        if (x == maxCnt) {
            answer++;
            return;
        }
        for (int i = 0; i < maxCnt; i++) {
            array[x] = i;
            if (canPush(array, x)) {
                track(x + 1, maxCnt, array);
            }
        }
    }

    static boolean canPush(int[] array, int currentX) {
        for (int i = 0; i < currentX; i++) {
            if (array[currentX] == array[i]) {
                return false;
            }
            if (isDiagonal(i, currentX, array)) {
                return false;
            }
        }
        return true;
    }

    static boolean isDiagonal(int i, int j, int[] array) {
        return Math.abs(array[i] - array[j]) == Math.abs(i - j);
    }
}
