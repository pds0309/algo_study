package 백준.backtracking;

import java.util.*;
import java.util.stream.Collectors;

public class boj15663_N과M9 {

    private static int[] array;
    private static List<String> answerSet = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] indexes = strToIntArr(scanner.nextLine());
        array = strToIntArr(scanner.nextLine());

        Arrays.sort(array);
        Set<Integer> set = new HashSet<>();
        permutation(indexes[0], indexes[1], 0, new int[indexes[1]], set);
        System.out.println(answerSet.stream().distinct().collect(Collectors.joining("\n")));
    }

    private static void permutation(int max, int loop, int cnt, int[] arr, Set<Integer> set) {
        if (cnt == loop) {
            answerSet.add(Arrays.stream(arr)
                    .mapToObj(v -> v + "")
                    .collect(Collectors.joining(" ")));
            return;
        }

        for (int i = 1; i <= max; i++) {
            arr[cnt] = array[i - 1];
            if (set.contains(i)) {
                continue;
            }
            set.add(i);
            permutation(max, loop, cnt + 1, arr, set);
            set.remove(i);
        }
    }

    private static int[] strToIntArr(String str) {
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
