package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1647_도시분할계획 {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        parents = new int[infos[0] + 1];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        Edge[] edges = new Edge[infos[1]];
        for (int i = 0; i < infos[1]; i++) {
            int[] edgeInfo = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edges[i] = new Edge(edgeInfo[0], edgeInfo[1], edgeInfo[2]);
        }
        Arrays.sort(edges);
        int answer = 0;
        int cnt = 0;
        for (Edge current : edges) {
            if (find(current.x) == find(current.y)) {
                continue;
            }
            union(current.x, current.y);
            if (cnt >= infos[0] - 2) {
                break;
            }
            answer += current.dist;
            cnt++;
        }
        System.out.println(answer);
    }

    static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
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

    static class Edge implements Comparable<Edge> {
        int x;
        int y;
        int dist;

        public Edge(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
}
