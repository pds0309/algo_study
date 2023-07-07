package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj1967_트리의_지름 {
    static Edge maxEdge;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bufferedReader.readLine());
        if(len == 1) {
            System.out.println(0);
            return;
        }
        List<Edge>[] lists = new List[len + 1];
        for (int i = 1; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < len - 1; i++) {
            int[] edgeInfo = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            lists[edgeInfo[0]].add(new Edge(edgeInfo[0], edgeInfo[1], edgeInfo[2]));
            lists[edgeInfo[1]].add(new Edge(edgeInfo[1], edgeInfo[0], edgeInfo[2]));
        }
        Edge startEdge = new Edge(0, 1, 0);
        dfs(startEdge, lists, 0, 0);
        startEdge = new Edge(0, maxEdge.next, 0);
        maxEdge = null;
        max = 0;
        dfs(startEdge, lists, 0, 0);
        System.out.println(max);
    }

    static void dfs(Edge start, List<Edge>[] lists, int dist, int cnt) {
        if (dist > max) {
            max = dist;
            maxEdge = start;
        }
        for (int i = 0; i < lists[start.next].size(); i++) {
            Edge next = lists[start.next].get(i);
            if (next.next == start.current) {
                continue;
            }
            dfs(next, lists, dist + next.dist, cnt + 1);
        }
    }

    static class Edge {
        int current;
        int next;
        int dist;

        public Edge(int current, int next, int dist) {
            this.current = current;
            this.next = next;
            this.dist = dist;
        }
    }
}
