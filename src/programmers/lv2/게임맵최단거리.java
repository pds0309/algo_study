package programmers.lv2;

import java.util.ArrayDeque;
import java.util.Queue;

public class 게임맵최단거리 {
    public static void main(String[] args) {
        int[][] maps = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        int[][] maps2 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        System.out.println(solution(maps));
    }

    private static int[] X = {1, 0, -1, 0};
    private static int[] Y = {0, 1, 0, -1};

    public static int solution(int[][] maps) {
        Point[][] map = new Point[maps.length + 2][maps[0].length + 2];
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[i].length - 1; j++) {
                map[i][j] = maps[i - 1][j - 1] == 1 ? new Point(Integer.MAX_VALUE, i, j) : new Point(0, i, j);
            }
        }
        bfs(map);
        if (map[map.length - 2][map[0].length - 2].value == Integer.MAX_VALUE) {
            return -1;
        }
        return map[map.length - 2][map[0].length - 2].value;
    }

    private static void bfs(Point[][] map) {
        Queue<Point> queue = new ArrayDeque<>();
        map[1][1].value = 1;
        queue.add(map[1][1]);
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.visited) {
                continue;
            }
            current.visited = true;
            int x = current.x;
            int y = current.y;
            for (int i = 0; i < X.length; i++) {
                int newX = x + X[i];
                int newY = y + Y[i];
                if (map[newX][newY] != null && map[newX][newY].value != 0) {
                    map[newX][newY].value = Math.min(map[newX][newY].value, current.value + 1);
                    queue.add(map[newX][newY]);
                }
            }
        }
    }

    static class Point {
        int value = 0;
        int x;
        int y;
        boolean visited = false;

        public Point(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }
    }
}
