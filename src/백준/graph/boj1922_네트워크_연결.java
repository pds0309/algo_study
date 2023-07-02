package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class boj1922_네트워크_연결 {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int nodeNum = Integer.parseInt(bufferedReader.readLine());
        int edgeNum = Integer.parseInt(bufferedReader.readLine());
        parents = new int[nodeNum + 1];
        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < edgeNum; i++) {
            int[] edges = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edgeList.add(new Edge(edges[0], edges[1], edges[2]));
        }
        int result = tv(edgeList.stream().sorted().collect(Collectors.toList()), nodeNum);
        System.out.println(result);
    }

    static int tv(List<Edge> edgeList, int nodeNum) {
        int answer = 0;
        int cnt = 0;
        for (Edge edge : edgeList) {
            if (find(edge.start) == find(edge.end)) {
                continue;
            }
            union(edge.start, edge.end);
            answer += edge.dist;
            cnt++;
            if (nodeNum - 1 == cnt) {
                break;
            }
        }
        return answer;
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

    static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return find(parents[x]);
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        }
        if (x <= y) {
            parents[y] = x;
            return true;
        }
        parents[x] = y;
        return true;
    }
}
