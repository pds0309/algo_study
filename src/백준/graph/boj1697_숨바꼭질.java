package 백준.graph;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class boj1697_숨바꼭질 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = arr[0];
        int end = arr[1];
        bfs(start, end);
    }

    private static void bfs(int start, int end) {
        Node startNode = new Node(start, 0);
        boolean[] visited = new boolean[100001];
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.count - o2.count);
        queue.add(startNode);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.location == end) {
                System.out.println(current.count);
                return;
            }
            visited[current.location] = true;
            if (current.location * 2 <= 100000 && !visited[current.location * 2]) {
                queue.add(new Node(current.location * 2, current.count + 1));
            }
            if (current.location + 1 <= 100000 && !visited[current.location + 1]) {
                queue.add(new Node(current.location + 1, current.count + 1));
            }
            if (current.location - 1 >= 0 && !visited[current.location - 1]) {
                queue.add(new Node(current.location - 1, current.count + 1));
            }
        }
    }

    static class Node {
        int location;
        int count;

        public Node(int location, int count) {
            this.location = location;
            this.count = count;
        }
    }
}
