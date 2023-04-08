package 백준.graph;

import java.util.*;

public class boj16953_A에서B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long[] arr = Arrays.stream(scanner.nextLine().split(" ")).mapToLong(Integer::parseInt).toArray();
        bfs(arr[1], arr[0]);
    }

    private static void bfs(long answer, long start) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 1));
        Set<Long> set = new HashSet<>();
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.value == answer) {
                System.out.println(current.count);
                return;
            }
            if (set.contains(current)) {
                continue;
            }
            set.add(current.value);
            long next1 = Long.parseLong(current.value + "1");
            if (next1 <= answer) {
                queue.add(new Node(next1, current.count + 1));
            }
            long next2 = current.value * 2;
            if (next2 <= answer) {
                queue.add(new Node(next2, current.count + 1));
            }
        }
        System.out.println("-1");
    }

    static class Node {
        long value;
        int count;

        public Node(long value, int count) {
            this.value = value;
            this.count = count;
        }
    }
}
