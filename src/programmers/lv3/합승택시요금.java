package programmers.lv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class 합승택시요금 {
    public static void main(String[] args) {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        System.out.println(solution(n, s, a, b, fares));
    }

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        List<Edge>[] lists = new List[n + 1];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int[] fare : fares) {
            lists[fare[0]].add(new Edge(fare[1], fare[2]));
            lists[fare[1]].add(new Edge(fare[0], fare[2]));
        }
        int[] aCosts = new int[n + 1];
        int[] bCosts = new int[n + 1];
        int[] sCosts = new int[n + 1];
        Arrays.fill(aCosts, Integer.MAX_VALUE);
        Arrays.fill(bCosts, Integer.MAX_VALUE);
        Arrays.fill(sCosts, Integer.MAX_VALUE);
        bfs(lists, a, aCosts);
        bfs(lists, b, bCosts);
        bfs(lists, s, sCosts);
        return IntStream.range(1, aCosts.length)
                .map(idx -> aCosts[idx] + bCosts[idx] + sCosts[idx])
                .min()
                .orElse(0);
    }

    private static void bfs(List<Edge>[] lists, int start, int[] costs) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(start, 0));
        costs[start] = 0;

        while (!priorityQueue.isEmpty()) {
            Edge current = priorityQueue.poll();
            if (current.distance > costs[current.link]) {
                continue;
            }
            for (Edge next : lists[current.link]) {
                int cost = costs[current.link] + next.distance;
                if (costs[next.link] > cost) {
                    costs[next.link] = cost;
                    priorityQueue.offer(new Edge(next.link, cost));
                }
            }
        }
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
