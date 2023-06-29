package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj1238_파티 {
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int nodeNum = Integer.parseInt(stringTokenizer.nextToken(" "));
        int caseNum = Integer.parseInt(stringTokenizer.nextToken(" "));
        int endNode = Integer.parseInt(stringTokenizer.nextToken(" "));
        List<Edge>[] lists = new List[nodeNum + 1];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < caseNum; i++) {
            int[] cases = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            lists[cases[0]].add(new Edge(cases[1], cases[2]));
        }
        for (int i = 1; i < lists.length; i++) {
            int[] dists = new int[lists.length];
            Arrays.fill(dists, Integer.MAX_VALUE);
            answer = Math.max(answer, bfs(lists, i, endNode, dists) + bfs(lists, endNode, i, dists));
        }
        System.out.println(answer);
    }

    static int bfs(List<Edge>[] lists, int start, int endNode, int[] distArr) {
        int[] dists = distArr.clone();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(start, 0));
        while (!priorityQueue.isEmpty()) {
            Edge current = priorityQueue.poll();
            if (current.link == endNode) {
                return current.distance;
            }
            for (Edge next : lists[current.link]) {
                int dist = next.distance + current.distance;
                if (dists[next.link] > dist) {
                    dists[next.link] = dist;
                    priorityQueue.add(new Edge(next.link, dist));
                }
            }
        }
        return 0;
    }

    static class Edge implements Comparable<Edge> {
        int link;
        int distance;

        public Edge(int link, int distance) {
            this.link = link;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.distance;
        }
    }
}
