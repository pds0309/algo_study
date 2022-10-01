package programmers.lv2;


import java.util.*;
import java.util.stream.IntStream;

public class 배달 {
    public static void main(String[] args) {
        int[][] road = {{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}};
        int N = 6;
        int K = 4;
        System.out.println(solution(N, road, K));
    }

    // K 이하 시간
    public static int solution(int N, int[][] road, int K) {

        List<Node>[] map = new List[N + 1];
        int[] distanceArray = IntStream.range(0, N + 1)
                .map(operand -> operand == 1 ? 0 : Integer.MAX_VALUE)
                .toArray();
        for (int i = 0; i < map.length; i++) {
            map[i] = new ArrayList<>();
        }

        for (int[] ints : road) {
            map[ints[0]].add(new Node(ints[1], ints[2]));
            map[ints[1]].add(new Node(ints[0], ints[2]));
        }
        bfs(map, new boolean[N + 1], 1, distanceArray);

        return (int) Arrays.stream(distanceArray)
                .filter(value -> value <= K)
                .count();
    }

    static void bfs(List<Node>[] map, boolean[] visited, int start, int[] distanceArray) {
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (visited[current.next]) {
                continue;
            }
            visited[current.next] = true;
            for (Node node : map[current.next]) {
                distanceArray[node.next] = Math.min(distanceArray[node.next], distanceArray[current.next] + node.dist);
                queue.add(new Node(node.next, distanceArray[node.next]));
            }
        }
    }

    static class Node {
        int next;
        int dist;

        public Node(int next, int dist) {
            this.next = next;
            this.dist = dist;
        }
    }
}
