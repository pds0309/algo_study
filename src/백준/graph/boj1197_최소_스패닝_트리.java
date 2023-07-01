package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class boj1197_최소_스패닝_트리 {
    static int[] parents;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infoArr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int nodeNum = infoArr[0];
        int caseNum = infoArr[1];
        parents = new int[nodeNum + 1];
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < caseNum; i++) {
            int[] edges = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edgeList.add(new Edge(edges[0], edges[1], edges[2]));
        }
        System.out.println(trav(edgeList.stream().sorted().collect(Collectors.toList()), nodeNum));
    }

    static int trav(List<Edge> edgeList, int nodeNum) {
        int answer = 0;
        int cnt = 0;
        for (Edge current : edgeList) {
            int startParent = find(current.start);
            if (startParent == find(current.end)) {
                continue;
            }
            union(current.start, current.end);
            answer += current.dist;
            cnt++;
            if (cnt == nodeNum - 1) {
                break;
            }
        }
        return answer;
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

    static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return find(parents[x]);
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
