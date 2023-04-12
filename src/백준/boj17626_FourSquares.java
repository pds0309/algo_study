package 백준;

import java.util.Scanner;

public class boj17626_FourSquares {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int value = Integer.parseInt(scanner.nextLine());
        int[] array = new int[50001];
        array[1] = 1;
        array[2] = 2;
        if (value <= 2) {
            System.out.println(array[value]);
            return;
        }
        for (int i = 3; i <= value; i++) {
            if (Math.sqrt(i) - Math.floor(Math.sqrt(i)) == 0) {
                array[i] = 1;
                continue;
            }
            array[i] = getMinValue(array, i);
        }
        System.out.println(array[value]);
    }

    private static int getMinValue(int[] array, int range) {
        int min = Integer.MAX_VALUE;
        for (int j = 1; j < Math.sqrt(range); j++) {
            min = Math.min(array[j * j] + array[range - j * j], min);
        }
        return min;
    }

}
