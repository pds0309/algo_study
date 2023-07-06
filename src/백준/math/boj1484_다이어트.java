package 백준.math;

import java.util.Scanner;

public class boj1484_다이어트 {
    public static void main(String[] args) {
        int G = Integer.parseInt(new Scanner(System.in).nextLine());
        StringBuilder stringBuilder = new StringBuilder();
        // G = x^2 - y^2
        int xIndex = 1;
        int yIndex = 1;
        while (true) {
            int current = xIndex * xIndex - yIndex * yIndex;
            if (isEnd(xIndex, yIndex, current, G)) {
                System.out.println(stringBuilder.length() == 0 ? -1 : stringBuilder);
                return;
            }
            if (current < G) {
                xIndex++;
                continue;
            }
            if (current > G) {
                yIndex++;
                continue;
            }
            stringBuilder.append(xIndex).append("\n");
            xIndex++;
        }
    }

    static boolean isEnd(int xIndex, int yIndex, int rest, int G) {
        return xIndex - yIndex == 1 && rest > G;
    }
}
