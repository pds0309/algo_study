package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class boj1753_최단경로 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int startNode = Integer.parseInt(bufferedReader.readLine());
        int nodeCnt = infos[0];
        int edgeNum = infos[1];
        List<Edge>[] lists = new List[nodeCnt + 1];
        for (int i = 1; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < edgeNum; i++) {
            int[] edgeInfo = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            lists[edgeInfo[0]].add(new Edge(edgeInfo[1], edgeInfo[2]));
        }
        int[] visited = new int[nodeCnt + 1];
        bfs(lists, startNode, visited);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < visited.length; i++) {
            if (i == startNode) {
                stringBuilder.append("0").append("\n");
                continue;
            }
            if (visited[i] == 0) {
                stringBuilder.append("INF").append("\n");
                continue;
            }
            stringBuilder.append(visited[i]).append("\n");
        }
        System.out.println(stringBuilder);
    }

    static void bfs(List<Edge>[] lists, int start, int[] visited) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(start, -1));
        while (!priorityQueue.isEmpty()) {
            Edge current = priorityQueue.poll();
            if (visited[current.to] != 0) {
                continue;
            }
            visited[current.to] = current.dist;
            for (Edge next : lists[current.to]) {
                priorityQueue.add(new Edge(next.to, next.dist + Math.max(current.dist, 0)));
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int to;
        int dist;

        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
}
