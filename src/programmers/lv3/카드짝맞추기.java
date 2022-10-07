package programmers.lv3;

import java.util.*;

public class 카드짝맞추기 {
    public static void main(String[] args) {
        int[][] board = {{3, 0, 0, 2}, {0, 0, 1, 0}, {0, 1, 0, 0}, {2, 0, 0, 3}};
        int[][] board2 = {{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}};
        int r2 = 1;
        int c2 = 0;
        int r = 0;
        int c = 1;
//        System.out.println(solution(board, r, c));
        System.out.println(solution(board2, r2, c2));
    }

    // 0: 카드없음
    // 1~6: 카드번호
    static Direction[] directions = Direction.values();

    public static int solution(int[][] board, int r, int c) {
        int[] pointArray = Arrays.stream(board)
                .flatMapToInt(Arrays::stream)
                .filter(value -> value != 0)
                .distinct()
                .toArray();
        pe(pointArray, new HashSet<>(), 0, "");
        for (int[] ints : pointList) {
            bfs(ints, board, r, c);
        }
        return result + ((pointArray.length) * 2);
    }

    static List<int[]> pointList = new ArrayList<>();

    private static void pe(int[] pointArray, Set<Integer> visited, int count, String temp) {
        if (pointArray.length == count) {
            pointList.add(temp.chars().map(operand -> operand - 48).toArray());
            return;
        }
        for (int j : pointArray) {
            if (!visited.contains(j)) {
                visited.add(j);
                pe(pointArray, visited, count + 1, temp + j);
                visited.remove(j);
            }
        }
    }

    static int answer = 0;
    static int result = Integer.MAX_VALUE;

    private static void bfs(int[] pointArray, int[][] board, int r, int c) {
        Point[][] points = new Point[board.length + 2][board[0].length + 2];
        for (int i = 1; i < points.length - 1; i++) {
            for (int j = 1; j < points[0].length - 1; j++) {
                points[i][j] = new Point(i, j, board[i - 1][j - 1]);
            }
        }
        Point start = points[r + 1][c + 1];
        int answer = 0;
        int distance = 0;
        int phase = 0;
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);
        start.visited[phase] = true;
        boolean second = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point current = queue.poll();
                if (current != null && current.id == pointArray[phase]) {
                    current.id = 0;
                    answer += distance;
                    distance = -1;
                    Arrays.stream(points).flatMap(Arrays::stream)
                            .filter(Objects::nonNull)
                            .forEach(point -> {
                                point.visited = new boolean[6];
                            });
                    queue.clear();
                    queue.add(current);
                    current.visited[phase] = true;
                    if (!second) {
                        second = true;
                    } else {
                        second = false;
                        phase++;
                        if (phase >= pointArray.length) {
                            result = Math.min(result, answer);
                            return;
                        }
                    }
                    break;
                }
                for (Direction direction : directions) {
                    Point jumpPoint = Command.JUMP.go(current, direction, points);
                    if (!jumpPoint.visited[phase]) {
                        jumpPoint.visited[phase] = true;
                        queue.add(jumpPoint);
                    }
                }
                for (Direction direction : directions) {
                    Point onePoint = Command.ONCE.go(current, direction, points);
                    if (onePoint != null) {
                        if (!onePoint.visited[phase]) {
                            onePoint.visited[phase] = true;
                            queue.add(onePoint);
                        }
                    }
                }
            }
            distance++;
        }
    }

    static class Point {
        int x;
        int y;
        int id;
        boolean[] visited = new boolean[6];
        boolean deleted = false;

        public Point(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }

    enum Command implements Move {
        ONCE((currentPoint, direction, points) -> {
            return points[currentPoint.x + direction.x][currentPoint.y + direction.y];
        }), JUMP((currentPoint, direction, points) -> {
            Point temp = currentPoint;
            int tempX = temp.x;
            int tempY = temp.y;
            while (true) {
                tempX = temp.x;
                tempY = temp.y;
                temp = points[temp.x + direction.x][temp.y + direction.y];
                if (temp != null && temp.id != 0) {
                    return temp;
                }
                if (temp == null) {
                    return points[tempX][tempY];
                }
            }
        });

        private final Move move;

        Command(Move move) {
            this.move = move;
        }

        @Override
        public Point go(Point currentPoint, Direction direction, Point[][] points) {
            return this.move.go(currentPoint, direction, points);
        }
    }

    interface Move {
        Point go(Point currentPoint, Direction direction, Point[][] points);
    }

    enum Direction {
        LEFT(0, -1), RIGHT(0, 1), UP(-1, 0), DOWN(1, 0);

        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
