package 백준.backtracking;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class boj15657_N과M8 {
    private static final StringBuilder stringBuilder = new StringBuilder();
    private static int[] array;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] indexes = strToIntArr(scanner.nextLine());
        array = strToIntArr(scanner.nextLine());
        Arrays.sort(array);
        permutation(indexes[0], indexes[1], 0, new int[indexes[1]]);
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
            if (cnt != 0 && arr[cnt - 1] > array[i - 1]) {
                continue;
            }
            arr[cnt] = array[i - 1];
            permutation(max, loop, cnt + 1, arr);
        }
    }

    private static int[] strToIntArr(String str) {
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

}
