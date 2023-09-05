package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj11724_연결_요소의_개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int pointNum = infos[0];
        int edgeNum = infos[1];
        List<Integer>[] lists = new ArrayList[pointNum + 1];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < edgeNum; i++) {
            int[] points = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            lists[points[0]].add(points[1]);
            lists[points[1]].add(points[0]);
        }
        boolean[] visited = new boolean[pointNum + 1];
        int ans = 0;
        for (int i = 1; i < lists.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            dfs(visited, i, lists);
            ans++;
        }
        System.out.println(ans);
    }

    static void dfs(boolean[] visited, int curr, List<Integer>[] lists) {
        for (int next : lists[curr]) {
            if (visited[next]) {
                continue;
            }
            visited[next] = true;
            dfs(visited, next, lists);
        }
    }
}
