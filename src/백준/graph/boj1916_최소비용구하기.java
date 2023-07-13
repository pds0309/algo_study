package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class boj1916_최소비용구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cityNum = Integer.parseInt(br.readLine());
        int busNum = Integer.parseInt(br.readLine());
        List<DestNode>[] list = new List[cityNum + 1];
        for (int i = 1; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < busNum; i++) {
            int[] arr = strToIntArr(br.readLine());
            list[arr[0]].add(new DestNode(arr[1], arr[2]));
        }
        int[] startEnd = strToIntArr(br.readLine());
        dijkstra(startEnd[0], startEnd[1], list);
    }

    private static void dijkstra(int start, int end, List<DestNode>[] list) {
        PriorityQueue<DestNode> priorityQueue = new PriorityQueue<>();
        int[] dist = IntStream.range(0, list.length + 1).map(i -> Integer.MAX_VALUE).toArray();
        priorityQueue.add(new DestNode(start, 0));
        while (!priorityQueue.isEmpty()) {
            DestNode current = priorityQueue.poll();
            if (current.next == end) {
                System.out.println(current.dist);
                return;
            }
            for (DestNode next : list[current.next]) {
                if (dist[next.next] > current.dist + next.dist) {
                    dist[next.next] = current.dist + next.dist;
                    priorityQueue.add(new DestNode(next.next, dist[next.next]));
                }
            }
        }
    }

    static class DestNode implements Comparable<DestNode> {
        int next;
        int dist;

        public DestNode(int next, int dist) {
            this.next = next;
            this.dist = dist;
        }

        @Override
        public int compareTo(DestNode o) {
            return this.dist - o.dist;
        }
    }

    private static int[] strToIntArr(String str) {
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
