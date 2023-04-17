package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj10026_적록색약 {
    static final int[] X = {0, 0, -1, 1};
    static final int[] Y = {1, -1, 0, 0};
    static Map<People, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bufferedReader.readLine());
        String[][] arr = new String[len][len];
        for (int i = 0; i < len; i++) {
            arr[i] = bufferedReader.readLine().split("");
        }
        Arrays.stream(People.values()).forEach(people -> {
            boolean[][] visited = new boolean[len][len];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (visited[i][j]) {
                        continue;
                    }
                    bfs(arr, visited, i, j, people);
                    map.putIfAbsent(people, 0);
                    map.compute(people, (people1, integer) -> integer + 1);
                }
            }
        });
        System.out.println(map.get(People.NORMAL) + " " + map.get(People.ABNORMAL));
    }

    private static void bfs(String[][] arr, boolean[][] visited, int x, int y, People people) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x, y));
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (visited[current.x][current.y]) {
                continue;
            }
            visited[current.x][current.y] = true;
            for (int i = 0; i < 4; i++) {
                int newX = current.x + X[i];
                int newY = current.y + Y[i];
                if (!validRange(newX, newY, arr.length)) {
                    continue;
                }
                if (!people.validate(arr[current.x][current.y], arr[newX][newY])) {
                    continue;
                }
                queue.add(new Point(newX, newY));
            }
        }
    }

    private static boolean validRange(int x, int y, int len) {
        return x >= 0 && y >= 0 && x < len && y < len;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    enum People {
        NORMAL((cur, next) -> {
            return cur.equals(next);
        }),
        ABNORMAL((cur, next) -> {
            if (cur.equals("R")) {
                return !next.equals("B");
            }
            if (cur.equals("G")) {
                return !next.equals("B");
            }
            return cur.equals(next);
        });
        final Validator validator;

        People(Validator validator) {
            this.validator = validator;
        }

        public boolean validate(String cur, String next) {
            return this.validator.validate(cur, next);
        }
    }

    @FunctionalInterface
    interface Validator {
        boolean validate(String cur, String next);
    }
}
