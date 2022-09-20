package programmers.lv3;

import java.util.*;

public class 블록이동하기 {

    static Set<Robot> robotHistorySet = new HashSet<>();
    static int answer;

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
        int[][] board2 = {{0, 0, 0}, {0, 0, 0}};
        System.out.println(solution(board));
    }

    public static int solution(int[][] board) {
        int[][] bd = setBoard(board);
        bfs(bd);
        return answer;
    }

    private static void bfs(int[][] board) {
        int[] X = {1, -1, 0, 0};
        int[] Y = {0, 0, 1, -1};
        int[] rotate = {-1, 1};
        Queue<Robot> q = new LinkedList<>();
        put(1, 1, 1, 2, q);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                Robot current = q.poll();
                if ((current.x1 == board.length - 2 && current.y1 == board[0].length - 2)
                        || (current.x2 == board.length - 2 && current.y2 == board[0].length - 2)) {
                    return;
                }
                for (int j = 0; j < X.length; j++) {
                    int x1 = current.x1 + X[j];
                    int y1 = current.y1 + Y[j];
                    int x2 = current.x2 + X[j];
                    int y2 = current.y2 + Y[j];
                    if (isValidPoint(x1, x2, y1, y2, board)) {
                        put(x1, y1, x2, y2, q);
                    }
                }
                if (!isVertical(current.x1, current.x2, current.y1, current.y2)) {
                    for (int r : rotate) {
                        int x1 = current.x1 + r;
                        int y1 = current.y1;
                        int x2 = current.x2 + r;
                        int y2 = current.y2;
                        if (isValidPoint(x1, x2, y1, y2, board)) {
                            put(current.x1, current.y1, x1, y1, q);
                            put(current.x2, current.y2, x2, y2, q);
                        }
                    }
                    continue;
                }

                for (int r : rotate) {
                    int x1 = current.x1;
                    int y1 = current.y1 + r;
                    int x2 = current.x2;
                    int y2 = current.y2 + r;
                    if (isValidPoint(x1, x2, y1, y2, board)) {
                        put(current.x1, current.y1, x1, y1, q);
                        put(current.x2, current.y2, x2, y2, q);
                    }
                }
            }
            answer++;
        }
    }

    private static void put(int x1, int y1, int x2, int y2, Queue<Robot> queue) {
        Robot robot = new Robot(x1, x2, y1, y2);
        if (robotHistorySet.contains(robot)) {
            return;
        }
        robotHistorySet.add(robot);
        queue.add(robot);
    }

    private static int[][] setBoard(int[][] board) {
        int[][] bd = new int[board.length + 2][board[0].length + 2];
        Arrays.fill(bd[0], 1);
        Arrays.fill(bd[bd.length - 1], 1);
        for (int i = 1; i < bd.length - 1; i++) {
            bd[i][0] = 1;
            bd[i][bd[i].length - 1] = 1;
            for (int j = 1; j < bd[0].length - 1; j++) {
                bd[i][j] = board[i - 1][j - 1];
            }
        }
        return bd;
    }

    private static boolean isVertical(int x1, int x2, int y1, int y2) {
        return y1 == y2 && x1 != x2;
    }

    private static boolean isValidPoint(int x1, int x2, int y1, int y2, int[][] board) {
        return board[x1][y1] == 0 && board[x2][y2] == 0;
    }

    static class Robot {
        int x1;
        int y1;
        int x2;
        int y2;

        public Robot(int x1, int x2, int y1, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Robot robot = (Robot) o;
            return x1 == robot.x1 && y1 == robot.y1 && x2 == robot.x2 && y2 == robot.y2
                    || x2 == robot.x1 && y2 == robot.y1 && x1 == robot.x2 && y1 == robot.y2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x1, y1, x2, y2);
        }
    }
}