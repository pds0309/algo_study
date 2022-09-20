package programmers.lv2;

import java.util.*;

public class 빛의경로사이클 {
    public static void main(String[] args) {
        String[] grid = {"SL", "LR"};
        String[] grid2 = {"S"};
        String[] grid3 = {"R", "R"};
        String[] grid4 = {"S", "S"};
        System.out.println(Arrays.toString(solution(grid4)));
    }
    //SL
    //LR

    static Mirror[][] mirrors;

    public static int[] solution(String[] grid) {
        mirrors = new Mirror[grid.length][grid[0].length()];
        for (int i = 0; i < mirrors.length; i++) {
            for (int j = 0; j < mirrors[0].length; j++) {
                mirrors[i][j] = new Mirror(String.valueOf(grid[i].charAt(j)));
            }
        }
        for (int i = 0; i < mirrors.length; i++) {
            for (int j = 0; j < mirrors[0].length; j++) {
                loop(i, j, new History(), From.LEFT);
                loop(i, j, new History(), From.RIGHT);
                loop(i, j, new History(), From.BOTTOM);
                loop(i, j, new History(), From.TOP);
            }
        }
        return answerList.stream().mapToInt(value -> value).sorted().toArray();
    }

    private static final List<Integer> answerList = new ArrayList<>();

    private static void loop(int currentX, int currentY, History history, From from) {
        while (true) {
            if (history.size != 0
                    && history.xList.get(0) == currentX && history.yList.get(0) == currentY
                    && history.froms.get(0).equals(from)) {
                answerList.add(history.size);
                return;
            }
            if (mirrors[currentX][currentY].fromContains(from)) {
                return;
            }
            mirrors[currentX][currentY].addFrom(from);
            history.set(from, currentX, currentY);
            Direction direction = mirrors[currentX][currentY].direction;
            From to = direction.move(from);
            currentX = setPoint(currentX, to.x, mirrors.length);
            currentY = setPoint(currentY, to.y, mirrors[0].length);
            from = to;
        }
    }

    private static int setPoint(int current, int newValue, int range) {
        if (current + newValue < 0) {
            return range - 1;
        }
        if (current + newValue >= range) {
            return 0;
        }
        return current + newValue;
    }

    static class Mirror {
        Direction direction;
        Set<From> set = new HashSet<>();

        public void addFrom(From from) {
            set.add(from);
        }

        public boolean fromContains(From from) {
            return set.contains(from);
        }

        public Mirror(String direction) {
            this.direction = Direction.valueOf(direction);
        }
    }

    enum Direction {
        L, S, R;

        public From move(From from) {
            if (this.equals(L)) {
                return from.moveLeft();
            }
            if (this.equals(R)) {
                return from.moveRight();
            }
            return from.moveSt();
        }
    }

    enum From {
        TOP(-1, 0), BOTTOM(1, 0), LEFT(0, -1), RIGHT(0, 1);

        private final int x;
        private final int y;

        From(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public From moveLeft() {
            if (this.equals(TOP)) {
                return LEFT;
            }
            if (this.equals(BOTTOM)) {
                return RIGHT;
            }
            if (this.equals(LEFT)) {
                return BOTTOM;
            }
            return TOP;
        }

        public From moveRight() {
            if (this.equals(TOP)) {
                return RIGHT;
            }
            if (this.equals(BOTTOM)) {
                return LEFT;
            }
            if (this.equals(LEFT)) {
                return TOP;
            }
            return BOTTOM;
        }

        public From moveSt() {
            return this;
        }
    }

    static class History {
        List<From> froms = new ArrayList<>();
        int size = 0;
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();

        public void set(From from, int x, int y) {
            this.froms.add(from);
            this.xList.add(x);
            this.yList.add(y);
            this.size++;
        }
    }
}
