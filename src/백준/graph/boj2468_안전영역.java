package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class boj2468_안전영역 {
    static int[] X = {0, 0, -1, 1};
    static int[] Y = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bufferedReader.readLine());
        int[][] arr = new int[len][len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int answer = 0;
        for (int height = 0; height <= 100; height++) {
            int partNum = 0;
            boolean[][] visited = new boolean[arr.length][arr.length];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    Point point = new Point(i, j);
                    if (visited[i][j] || isFlooded(arr, point, height)) {
                        continue;
                    }
                    bfs(height, arr, point, visited);
                    partNum++;
                }
            }
            if (partNum == 0) {
                System.out.println(height);
                break;
            }
            answer = Math.max(answer, partNum);
        }
        System.out.println(answer);
    }

    static void bfs(int height, int[][] arr, Point start, boolean[][] visited) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (visited[current.x][current.y]) {
                continue;
            }
            visited[current.x][current.y] = true;
            for (int i = 0; i < 4; i++) {
                Point next = new Point(current.x + X[i], current.y + Y[i]);
                if (!isValidRange(next, arr.length) || isFlooded(arr, next, height)) {
                    continue;
                }
                queue.add(next);
            }
        }
    }

    static boolean isFlooded(int[][] arr, Point point, int height) {
        return arr[point.x][point.y] - height <= 0;
    }

    static boolean isValidRange(Point point, int len) {
        return point.x >= 0 && point.y >= 0 && point.x < len && point.y < len;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
