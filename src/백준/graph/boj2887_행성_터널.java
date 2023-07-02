package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class boj2887_행성_터널 {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int nodeNum = Integer.parseInt(bufferedReader.readLine());
        parents = new int[nodeNum];
        List<Edge> edgeList = new ArrayList<>();
        Node[] nodeListByX = new Node[nodeNum];
        Node[] nodeListByY = new Node[nodeNum];
        Node[] nodeListByZ = new Node[nodeNum];
        for (int i = 0; i < nodeNum; i++) {
            int[] nodes = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = nodes[0];
            int y = nodes[1];
            int z = nodes[2];
            Node node = new Node(x, y, z, i);
            nodeListByX[i] = node;
            nodeListByY[i] = node;
            nodeListByZ[i] = node;
            parents[i] = i;
        }
        Arrays.sort(nodeListByX, Comparator.comparingInt(o -> o.x));
        Arrays.sort(nodeListByY, Comparator.comparingInt(o -> o.y));
        Arrays.sort(nodeListByZ, Comparator.comparingInt(o -> o.z));
        for (int i = 1; i < nodeListByX.length; i++) {
            edgeList.add(new Edge(nodeListByX[i].id, nodeListByX[i - 1].id, Math.abs(nodeListByX[i].x - nodeListByX[i - 1].x)));
            edgeList.add(new Edge(nodeListByY[i].id, nodeListByY[i - 1].id, Math.abs(nodeListByY[i].y - nodeListByY[i - 1].y)));
            edgeList.add(new Edge(nodeListByZ[i].id, nodeListByZ[i - 1].id, Math.abs(nodeListByZ[i].z - nodeListByZ[i - 1].z)));
        }
        List<Edge> sortedEdgeList = edgeList.stream().sorted().collect(Collectors.toList());
        int answer = 0;
        int cnt = 0;
        for (Edge edge : sortedEdgeList) {
            if (find(edge.startId) == find(edge.endId)) {
                continue;
            }
            union(edge.startId, edge.endId);
            answer += edge.dist;
            cnt++;
            if (cnt == nodeNum - 1) {
                break;
            }
        }
        System.out.println(answer);
    }

    static int find(int x) {
        if (x == parents[x]) {
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

    static class Node {
        int x;
        int y;
        int z;
        int id;

        public Node(int x, int y, int z, int id) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.id = id;
        }

        @Override
        public String toString() {
            return this.x + "," + this.y + "," + this.z;
        }
    }

    static class Edge implements Comparable<Edge> {
        int startId;
        int endId;
        int dist;

        public Edge(int startId, int endId, int dist) {
            this.startId = startId;
            this.endId = endId;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

}
