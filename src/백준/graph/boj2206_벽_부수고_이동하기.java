package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class boj2206_벽_부수고_이동하기 {
    static int[] X = {0, 0, 1, -1};
    static int[] Y = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        int answer = Integer.MAX_VALUE;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int xLen = infos[0];
        int yLen = infos[1];
        int[][] map = new int[xLen][yLen];
        for (int i = 0; i < xLen; i++) {
            map[i] = Arrays.stream(bufferedReader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        answer = Math.min(answer, bfs(map));
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer + 1);
    }

    static int bfs(int[][] array) {
        Queue<Point> queue = new ArrayDeque<>();
        int[][][] visited = new int[array.length][array[0].length][2];
        queue.add(new Point(0, 0, false));
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.x == array.length - 1 && current.y == array[0].length - 1) {
                return visited[current.x][current.y][current.isBreaked ? 1 : 0];
            }
            for (int i = 0; i < 4; i++) {
                int nextX = X[i] + current.x;
                int nextY = Y[i] + current.y;
                if (!isValidPoint(array.length, array[0].length, nextX, nextY)) {
                    continue;
                }
                boolean nextBreak = current.isBreaked;
                if (visited[nextX][nextY][nextBreak ? 1 : 0] != 0) {
                    continue;
                }
                if (current.isBreaked && array[nextX][nextY] == 1) {
                    continue;
                }
                if (array[nextX][nextY] == 1) {
                    nextBreak = true;
                }
                visited[nextX][nextY][nextBreak ? 1 : 0] = visited[current.x][current.y][current.isBreaked ? 1 : 0] + 1;
                queue.add(new Point(nextX, nextY, nextBreak));
            }
        }
        return Integer.MAX_VALUE;
    }

    static boolean isValidPoint(int xLen, int yLen, int x, int y) {
        return x >= 0 && y >= 0 && x < xLen && y < yLen;
    }

    static class Point {
        int x;
        int y;
        boolean isBreaked;

        public Point(int x, int y, boolean isBreaked) {
            this.x = x;
            this.y = y;
            this.isBreaked = isBreaked;
        }
    }
}
