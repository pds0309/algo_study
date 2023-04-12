package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class boj2178_미로탐색 {
    static final int[] X = {0, 1, -1, 0};
    static final int[] Y = {1, 0, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] xyArr = strToIntArr(bufferedReader.readLine(), " ");
        int[][] map = new int[xyArr[0] + 2][xyArr[1] + 2];
        boolean[][] visited = new boolean[xyArr[0] + 2][xyArr[1] + 2];
        for (int i = 1; i < map.length - 1; i++) {
            map[i] = strToIntArr("0" + bufferedReader.readLine() + "0", "");
        }
        bfs(map, visited);
    }

    private static void bfs(int[][] map, boolean[][] visited) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(1, 1, 1));
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.x == map.length - 2 && current.y == map[0].length - 2) {
                System.out.println(current.dist);
                return;
            }
            if (visited[current.x][current.y]) {
                continue;
            }
            visited[current.x][current.y] = true;
            for (int i = 0; i < 4; i++) {
                int newX = current.x + X[i];
                int newY = current.y + Y[i];
                if (isMovablePoint(newX, newY, map)) {
                    queue.add(new Point(newX, newY, current.dist + 1));
                }
            }
        }
    }

    private static boolean isMovablePoint(int x, int y, int[][] map) {
        return map[x][y] != 0;
    }

    private static int[] strToIntArr(String str, String regex) {
        return Arrays.stream(str.split(regex)).mapToInt(Integer::parseInt).toArray();
    }

    static class Point {
        int x;
        int y;
        int dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

}
