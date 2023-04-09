package 백준.backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class boj15654_N과M5 {
    private static final StringBuilder stringBuilder = new StringBuilder();
    private static int[] array;
    private static Set<Integer> set = new HashSet<>();

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
            if (cnt != 0 && arr[cnt - 1] == array[i - 1]) {
                continue;
            }
            if (set.contains(array[i - 1])) {
                continue;
            }
            arr[cnt] = array[i - 1];
            set.add(array[i - 1]);
            permutation(max, loop, cnt + 1, arr);
            set.remove(array[i - 1]);
        }
    }

    private static int[] strToIntArr(String str) {
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

}
