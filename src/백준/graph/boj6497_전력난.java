package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj6497_전력난 {
    static int[] parents;
    static int skippedRoadCost;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String currentRead = bufferedReader.readLine();
            if (currentRead.trim().equals("0 0")) {
                System.out.println(stringBuilder);
                return;
            }
            int[] infos = Arrays.stream(currentRead.split(" ")).mapToInt(Integer::parseInt).toArray();
            int nodeNum = infos[0];
            int edgeNum = infos[1];
            skippedRoadCost = 0;
            parents = new int[nodeNum];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
            Edge[] edges = new Edge[edgeNum];
            for (int i = 0; i < edgeNum; i++) {
                int[] edgeInfo = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                edges[i] = new Edge(edgeInfo[0], edgeInfo[1], edgeInfo[2]);
            }
            Arrays.sort(edges);
            for (Edge edge : edges) {
                if (find(edge.start) == find(edge.end)) {
                    skippedRoadCost += edge.dist;
                    continue;
                }
                union(edge.start, edge.end);
            }
            stringBuilder.append(skippedRoadCost).append("\n");
        }
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        }
        parents[y] = x;
        return true;
    }

    static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int dist;

        public Edge(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
}
