package programmers.lv2;

import java.util.ArrayList;
import java.util.List;

public class 전력망을둘로나누기 {

    public static void main(String[] args) {
        int n = 9;
        int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        System.out.println(solution(n, wires));
    }
    private static int tempCount;
    private static int answer = Integer.MAX_VALUE;

    public static int solution(int n, int[][] wires) {
        List<Integer>[] map = new List[n + 1];
        for (int i = 0; i < map.length; i++) {
            map[i] = new ArrayList<>();
        }
        for (int[] wire : wires) {
            int start = wire[0];
            int end = wire[1];
            map[start].add(end);
            map[end].add(start);
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].size(); j++) {
                List[] cloneMap = map.clone();
                List<Integer> currentTempList = new ArrayList<>(map[i]);
                if (currentTempList.isEmpty()) {
                    continue;
                }
                boolean[] visited = new boolean[n + 1];
                int start2 = currentTempList.get(j);
                currentTempList.remove(j);
                cloneMap[i] = currentTempList;
                dfs(cloneMap, i, visited);
                int x = tempCount;
                tempCount = 0;
                dfs(cloneMap, start2, visited);
                int y = tempCount;
                tempCount = 0;
                if (Math.abs(x - y) < answer) {
                    answer = Math.abs(x - y);
                }
            }
        }
        return answer;
    }

    private static void dfs(List<Integer>[] map, int start, boolean[] visited) {
        visited[start] = true;
        for (int adj : map[start]) {
            if (!visited[adj]) {
                tempCount++;
                dfs(map, adj, visited);
            }
        }
    }
}
