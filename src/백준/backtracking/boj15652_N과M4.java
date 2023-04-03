package 백준.backtracking;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class boj15652_N과M4 {

    private static final StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        permutation(arr[0], arr[1], 0, new int[arr[1]]);
        System.out.println(stringBuilder);
    }

    private static void permutation(int max, int loop, int cnt, int[] arr) {
        if (cnt == loop) {
            stringBuilder
                    .append(Arrays.stream(arr)
                            .mapToObj(v -> v + "")
                            .collect(Collectors.joining(" ")))
                    .append("\n");
            return;
        }
        for (int i = 1; i <= max; i++) {
            if (cnt != 0 && arr[cnt - 1] > i) {
                continue;
            }
            arr[cnt] = i;
            permutation(max, loop, cnt + 1, arr);
        }
    }

}
