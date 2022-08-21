package programmers.lv2;

import java.util.*;

public class 방문길이 {
    private static final int BOUND = 5;

    public static void main(String[] args) {
        String dirs = "ULURRDLLU";
        System.out.println(solution(dirs));
    }

    public static int solution(String dirs) {
        Set<Coordinate> coordinateSet = new HashSet<>();
        int[] start = {0, 0};
        for (int i = 0; i < dirs.length(); i++) {
            Move.valueOf(String.valueOf(dirs.charAt(i)))
                    .move(start)
                    .ifPresent(coordinate -> {
                        setArray(start, coordinate.endX, coordinate.endY);
                        coordinateSet.add(coordinate);
                    });
        }
        return coordinateSet.size();
    }

    private static void setArray(int[] array, int x, int y) {
        array[0] = x;
        array[1] = y;
    }

    enum Move implements CoordinateMoveStrategy {
        U(array -> array[1] == BOUND ? Optional.empty()
                : Optional.of(new Coordinate(array, new int[]{array[0], array[1] + 1}))),
        D(array -> array[1] == -BOUND ? Optional.empty()
                : Optional.of(new Coordinate(array, new int[]{array[0], array[1] - 1}))),
        R(array -> array[0] == BOUND ? Optional.empty()
                : Optional.of(new Coordinate(array, new int[]{array[0] + 1, array[1]}))),
        L(array -> array[0] == -BOUND ? Optional.empty()
                : Optional.of(new Coordinate(array, new int[]{array[0] - 1, array[1]})));

        private final CoordinateMoveStrategy coordinateStrategy;

        Move(CoordinateMoveStrategy coordinateStrategy) {
            this.coordinateStrategy = coordinateStrategy;
        }

        public Optional<Coordinate> move(int[] currentCoordinate) {
            return coordinateStrategy.move(currentCoordinate);
        }

    }

    @FunctionalInterface
    interface CoordinateMoveStrategy {
        Optional<Coordinate> move(int[] array);
    }


    static class Coordinate {
        final int startX;
        final int startY;
        final int endX;
        final int endY;

        public Coordinate(int[] start, int[] end) {
            this.startX = start[0];
            this.startY = start[1];
            this.endX = end[0];
            this.endY = end[1];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return (startX == that.startX && startY == that.startY && endX == that.endX && endY == that.endY)
                    || (startX == that.endX && startY == that.endY && endX == that.startX && endY == that.startY);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startX + startY + endX + endY);
        }
    }
}
