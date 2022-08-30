package programmers.lv2;

import java.util.Arrays;

public class 삼각달팽이 {
    public static void main(String[] args) {
        int n = 4;
        System.out.println(Arrays.toString(solution(n)));
    }

    private static int[][] array;
    private static int sum = 0;

    public static int[] solution(int n) {
        sum = ((n + 1) * (n)) / 2;
        array = new int[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            array[i][0] = -1;
            array[n + 1][i] = -1;
            array[0][i] = -1;
            array[i][n + 1] = -1;
        }
        array[1][n] = 1;
        set(2, Direction.D, 1, n);
        return Arrays.stream(array).flatMapToInt(v -> Arrays.stream(v).filter(va -> va > 0)).toArray();
    }

    private static void set(int currentNumber, Direction direction, int x, int y) {
        if (currentNumber > sum) {
            return;
        }
        while (true) {
            x += direction.x;
            y += direction.y;
            if (array[x][y] != 0) {
                break;
            }
            array[x][y] = currentNumber++;
        }
        set(currentNumber, Direction.valueOf(direction.next), x - direction.x, y - direction.y);
    }

    enum Direction {
        D(1, -1, "R"), R(0, 1, "U"), U(-1, 0, "D");
        private final int x;
        private final int y;
        private final String next;

        Direction(int x, int y, String next) {
            this.x = x;
            this.y = y;
            this.next = next;
        }
    }
}
