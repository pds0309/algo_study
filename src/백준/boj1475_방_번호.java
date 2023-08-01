package 백준;

import java.util.Arrays;
import java.util.Scanner;

public class boj1475_방_번호 {
    public static void main(String[] args) {
        String N = new Scanner((System.in)).nextLine();
        int[] arr = new int[10];
        for (int i = 0; i < N.length(); i++) {
            int current = N.charAt(i) - 48;
            if (current == 6 || current == 9) {
                if (arr[6] <= arr[9]) {
                    arr[6]++;
                } else {
                    arr[9]++;
                }
                continue;
            }
            arr[current]++;
        }
        System.out.println(Arrays.stream(arr).max().orElse(0));
    }
}
