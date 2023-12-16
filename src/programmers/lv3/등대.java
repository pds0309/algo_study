import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static int solution(int n, int[][] lighthouse) {
        int[][] dp = new int[n][2];
        List<Integer>[] nodes = new ArrayList[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int[] pair : lighthouse) {
            int a = pair[0] - 1;
            int b = pair[1] - 1;
            nodes[a].add(b);
            nodes[b].add(a);
        }

        dfs(0, nodes, dp, visited);
        return Math.min(dp[0][0], dp[0][1]);
    }

    private static void dfs(int node, List<Integer>[] nodes, int[][] dp, boolean[] visited) {
        visited[node] = true;
        List<Integer> leafList = new ArrayList<>();
        for (int leaf : nodes[node]) {
            if (visited[leaf]) {
                continue;
            }
            leafList.add(leaf);
            dfs(leaf, nodes, dp, visited);
        }
        dp[node][0] = 0;
        dp[node][1] = 1;
        for (int leaf : leafList) {
            dp[node][1] += Math.min(dp[leaf][0], dp[leaf][1]);
            dp[node][0] += dp[leaf][1];
        }
    }
}
