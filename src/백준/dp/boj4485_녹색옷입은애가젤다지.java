package 백준.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class boj4485_녹색옷입은애가젤다지 {
    static int[] X = {0, 0, 1, -1};
    static int[] Y = {1, -1, 0, 0};
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int phase = 0;
        while (true) {
            String str = bufferedReader.readLine();
            if ("0".equals(str)) {
                System.out.println(stringBuilder);
                return;
            }
            int len = Integer.parseInt(str);
            stringBuilder.append("Problem ").append(++phase).append(": ");
            int[][] array = new int[len][len];
            boolean[][] visited = new boolean[len][len];
            for (int i = 0; i < array.length; i++) {
                array[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            bfs(array, visited);
        }
    }

    static void bfs(int[][] array, boolean[][] visited) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(0, 0, array[0][0]));
        while (!priorityQueue.isEmpty()) {
            Edge current = priorityQueue.poll();
            if (visited[current.x][current.y]) {
                continue;
            }
            visited[current.x][current.y] = true;
            if (current.x == array.length - 1 && current.y == array.length - 1) {
                stringBuilder.append(current.val).append("\n");
                return;
            }
            for (int i = 0; i < 4; i++) {
                int newX = current.x + X[i];
                int newY = current.y + Y[i];
                if (isValidRange(array.length, newX, newY) && !visited[newX][newY]) {
                    priorityQueue.add(new Edge(newX, newY, array[newX][newY] + current.val));
                }
            }
        }
    }

    static boolean isValidRange(int len, int x, int y) {
        return x >= 0 && y >= 0 && x < len && y < len;
    }

    static class Edge implements Comparable<Edge> {
        int x;
        int y;
        int val;

        public Edge(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Edge o) {
            return this.val - o.val;
        }
    }
}
