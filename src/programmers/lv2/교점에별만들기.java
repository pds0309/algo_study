package programmers.lv2;

import java.util.*;
import java.util.stream.Collectors;

public class 교점에별만들기 {

    public static void main(String[] args) {
        int[][] line = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};
        System.out.println(Arrays.toString(solution(line)));
    }

    public static String[] solution(int[][] line) {
        List<Line> lineList = new ArrayList<>();
        for (int[] ints : line) {
            lineList.add(new Line(ints[0], ints[1], ints[2]));
        }
        lineList = lineList.stream().distinct().collect(Collectors.toList());
        Set<Point> pointSet = new HashSet<>();
        for (int i = 0; i < lineList.size(); i++) {
            Line currentLine = lineList.get(i);
            for (int j = 1; j < lineList.size(); j++) {
                currentLine.findIntersection(lineList.get(j))
                        .ifPresent(pointSet::add);
            }
        }

        Board board = new Board(pointSet);
        board.setBoard(pointSet);
        String[] result = new String[board.getBoard().length];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = String.valueOf(board.getBoard()[result.length - 1 - i]);
        }
        return result;
    }

    static class Line {
        private final long xPart;
        private final long yPart;
        private final long residual;

        public Line(int xPart, int yPart, int residual) {
            this.xPart = xPart;
            this.yPart = yPart;
            this.residual = residual;
        }

        public Optional<Point> findIntersection(Line other) {
            long divider = this.xPart * other.yPart - this.yPart * other.xPart;
            long x = this.yPart * other.residual - this.residual * other.yPart;
            long y = this.residual * other.xPart - this.xPart * other.residual;
            return Optional.ofNullable(Point.of(x, y, divider));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Line line = (Line) o;
            return xPart == line.xPart && yPart == line.yPart && residual == line.residual;
        }

        @Override
        public int hashCode() {
            return Objects.hash(xPart, yPart, residual);
        }
    }

    static class Point {
        private final int x;
        private final int y;

        private Point(long x, long y) {
            this.x = (int) x;
            this.y = (int) y;
        }

        public static Point of(long x, long y, long divider) {
            return !isParallel(divider) && validInteger(x, y, divider) ? new Point(x / divider, y / divider) : null;
        }

        private static boolean validInteger(long x, long y, long divider) {
            return (double) x / divider == x / divider && (double) y / divider == y / divider;
        }

        private static boolean isParallel(long divider) {
            return divider == 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Board {

        private final char[][] board;
        private final int startRowAdjustValue;
        private final int startColumnAdjustValue;

        public Board(Set<Point> pointSet) {
            int rowMinValue = pointSet.stream()
                    .min(Comparator.comparing(v -> v.x))
                    .orElse(new Point(0, 0)).x;
            int columnMinValue = pointSet.stream()
                    .min(Comparator.comparing(v -> v.y))
                    .orElse(new Point(0, 0)).y;
            int rowMaxValue = pointSet.stream()
                    .max(Comparator.comparing(v -> v.x))
                    .orElse(new Point(0, 0)).x;
            int columnMaxValue = pointSet.stream()
                    .max(Comparator.comparing(v -> v.y))
                    .orElse(new Point(0, 0)).y;
            this.startRowAdjustValue = -rowMinValue;
            this.startColumnAdjustValue = -columnMinValue;
            board = new char[columnMaxValue + startColumnAdjustValue + 1][rowMaxValue + startRowAdjustValue + 1];
            Arrays.stream(board).forEach(chars -> Arrays.fill(chars, '.'));
        }

        public void setBoard(Set<Point> pointSet) {
            pointSet.forEach(point -> board[point.y + startColumnAdjustValue][point.x + startRowAdjustValue] = '*');
        }

        public char[][] getBoard() {
            return board;
        }
    }
}
