package 백준.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class boj2668_숫자고르기 {
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = Integer.parseInt(scanner.nextLine());
        int[] array = new int[len + 1];
        for (int i = 1; i < len + 1; i++) {
            array[i] = Integer.parseInt(scanner.nextLine());
        }
        for (int i = 1; i < len + 1; i++) {
            boolean[] visited = new boolean[len + 1];
            if (!visited[i]) {
                visited[i] = true;
                dfs(array, visited, i, array[i], new StringBuilder().append(array[i]));
            }
        }
        System.out.println(set.size());
        set.stream().sorted().forEach(System.out::println);
    }

    static void dfs(int[] arr, boolean[] visited, int start, int cur, StringBuilder sb) {
        if (start == cur) {
            set.addAll(Arrays.stream(sb.toString().split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
            return;
        }
        if (visited[cur]) {
            return;
        }
        visited[cur] = true;
        dfs(arr, visited, start, arr[cur], sb.append(",").append(arr[cur]));
    }
}
