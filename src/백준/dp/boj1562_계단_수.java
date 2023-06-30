package 백준.dp;

import java.util.Scanner;

public class boj1562_계단_수 {
    static int[][][] dpArray;

    public static void main(String[] args) {
        int length = Integer.parseInt(new Scanner(System.in).nextLine());
        if (length < 10) {
            System.out.println(0);
            return;
        }
        dpArray = new int[10][length + 1][1024];
        long answer = 0;
        for (int i = 1; i <= 9; i++) {
            answer += dfs(i, 1 << i, 1, length);
            answer %= 1000000000;
        }
        System.out.println(answer);
    }

    static int dfs(int startNumber, int currentBit, int currentLength, int maxLength) {
        if (dpArray[startNumber][currentLength][currentBit] != 0) {
            return dpArray[startNumber][currentLength][currentBit];
        }
        if (maxLength == currentLength) {
            if (currentBit != 1023) {
                return 0;
            }
            return 1;
        }
        int tempCount = 0;
        if (startNumber < 9) {
            int increasedNum = startNumber + 1;
            tempCount += dfs(increasedNum, currentBit | (1 << increasedNum), currentLength + 1, maxLength);
        }
        if (startNumber > 0) {
            int decreasedNum = startNumber - 1;
            tempCount += dfs(decreasedNum, currentBit | (1 << decreasedNum), currentLength + 1, maxLength);
        }
        tempCount %= 1000000000;
        dpArray[startNumber][currentLength][currentBit] = tempCount;
        return dpArray[startNumber][currentLength][currentBit];
    }

}
