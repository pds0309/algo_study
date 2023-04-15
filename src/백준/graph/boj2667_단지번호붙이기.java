package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class boj2667_단지번호붙이기 {

    static int count = 0;

    static List<Integer> list = new ArrayList<>();

    static int[] X = {0, 0, 1, -1};
    static int[] Y = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bufferedReader.readLine());
        int[][] arr = new int[len + 2][len + 2];
        boolean[][] visited = new boolean[len + 2][len + 2];
        for (int i = 1; i <= len; i++) {
            arr[i] = strToIntArr("0" + bufferedReader.readLine() + "0");
        }

        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = 1; j < arr[0].length - 1; j++) {
                if (visited[i][j] || arr[i][j] == 0) {
                    continue;
                }
                bfs(i, j, arr, visited);
            }
        }
        System.out.println(count);
        System.out.println(list.stream().sorted().map(Object::toString).collect(Collectors.joining("\n")));
    }

    private static void bfs(int x, int y, int[][] arr, boolean[][] visited) {
        count++;
        int num = 1;
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (int i = 0; i < 4; i++) {
                if (!visited[current.x + X[i]][current.y + Y[i]] && arr[current.x + X[i]][current.y + Y[i]] == 1) {
                    num++;
                    visited[current.x + X[i]][current.y + Y[i]] = true;
                    queue.add(new Point(current.x + X[i], current.y + Y[i]));
                }
            }
        }
        list.add(num);
    }

    private static int[] strToIntArr(String str) {
        return Arrays.stream(str.split("")).mapToInt(Integer::parseInt).toArray();
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
