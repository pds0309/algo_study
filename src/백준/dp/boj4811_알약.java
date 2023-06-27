package 백준.dp;

import java.util.Scanner;

public class boj4811_알약 {
    public static void main(String[] args) {
        long[] array = new long[31];
        Scanner scanner = new Scanner(System.in);
        array[0] = 1;
        array[1] = 1;
        array[2] = 2;
        for (int i = 3; i <= 30; i++) {
            array[i] = (2 * i * (2 * i - 1) * array[i - 1]) / (i * (i + 1));
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int currentIndex = Integer.parseInt(scanner.nextLine());
            if (currentIndex == 0) {
                System.out.println(stringBuilder);
                return;
            }
            stringBuilder.append(array[currentIndex]).append("\n");
        }
    }
}
