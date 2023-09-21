package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class boj1926_그림 {
    static int[] X = {1, -1, 0, 0};
    static int[] Y = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] lengths = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] arr = new int[lengths[0]][lengths[1]];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int max = 0;
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1) {
                    max = Math.max(max, bfs(arr, new Point(i, j)));
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
        System.out.println(max);
    }

    static int bfs(int[][] arr, Point startPoint) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(startPoint);
        int cnt = 0;
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (arr[current.x][current.y] == 0) {
                continue;
            }
            cnt++;
            arr[current.x][current.y] = 0;
            for (int i = 0; i < 4; i++) {
                int newX = current.x + X[i];
                int newY = current.y + Y[i];
                if (isValidRange(arr, newX, newY) && arr[newX][newY] == 1) {
                    queue.add(new Point(newX, newY));
                }
            }
        }
        return cnt;
    }

    static boolean isValidRange(int[][] arr, int x, int y) {
        return x >= 0 && y >= 0 && x < arr.length && y < arr[0].length;
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
