package programmers.lv3;

import java.util.*;

public class 아이템줍기 {
    public static void main(String[] args) {
        int[][] rectangle = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
        int cX = 1;
        int cY = 3;
        int itemX = 7;
        int itemY = 8;
        System.out.println(solution(rectangle, cX, cY, itemX, itemY));
    }

    private static final Command[] commands = Command.values();

    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        Point[][] pointArray = new Point[102][102];
        characterX = characterX * 2;
        characterY = characterY * 2;
        itemX = itemX * 2;
        itemY = itemY * 2;
        for (int i = 0; i < rectangle.length; i++) {
            for (int j = 0; j < rectangle[0].length; j++) {
                rectangle[i][j] = rectangle[i][j] * 2;
            }
        }
        for (int[] ints : rectangle) {
            setPointByRectangle(ints, pointArray);
        }
        pointArray[itemX][itemY].endPoint = true;
        pointArray[characterX][characterY].sumOfDistance = 0;
        bfs(pointArray, pointArray[characterX][characterY]);
        return pointArray[itemX][itemY].sumOfDistance / 2;
    }

    private static void bfs(Point[][] pointArray, Point start) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.visited) {
                continue;
            }
            current.visited = true;
            for (Command command : commands) {
                if (current.commandAvailableSet.contains(command)) {
                    int newX = current.x + command.x;
                    int newY = current.y + command.y;
                    if (pointArray[newX][newY] == null) {
                        continue;
                    }
                    pointArray[newX][newY].sumOfDistance = Math.min(pointArray[newX][newY].sumOfDistance, current.sumOfDistance + 1);
                    queue.add(pointArray[newX][newY]);
                }
            }
        }
    }

    static class Point {
        int x;
        int y;
        boolean visited = false;
        int sumOfDistance = Integer.MAX_VALUE;
        boolean endPoint = false;
        Set<Command> commandAvailableSet = new HashSet<>();

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void setPointByRectangle(int[] rect, Point[][] points) {
        int startXRange = rect[0];
        int endXRange = rect[2];
        int startYRange = rect[1];
        int endYRange = rect[3];

        for (int i = startXRange; i <= endXRange; i++) {
            points[i][startYRange] = points[i][startYRange] == null ? new Point(i, startYRange) : points[i][startYRange];
            points[i][endYRange] = points[i][endYRange] == null ? new Point(i, endYRange) : points[i][endYRange];
            if (startXRange != i) {
                points[i][startYRange].commandAvailableSet.add(Command.UP);
                points[i][endYRange].commandAvailableSet.add(Command.UP);
            }
            if (endXRange != i) {
                points[i][startYRange].commandAvailableSet.add(Command.DOWN);
                points[i][endYRange].commandAvailableSet.add(Command.DOWN);
            }
            points[i][startYRange].commandAvailableSet.remove(Command.RIGHT);
            points[i][endYRange].commandAvailableSet.remove(Command.LEFT);
        }
        for (int i = startYRange; i <= endYRange; i++) {
            points[startXRange][i] = points[startXRange][i] == null ? new Point(startXRange, i) : points[startXRange][i];
            points[endXRange][i] = points[endXRange][i] == null ? new Point(endXRange, i) : points[endXRange][i];
            if (startYRange != i) {
                points[startXRange][i].commandAvailableSet.add(Command.LEFT);
                points[endXRange][i].commandAvailableSet.add(Command.LEFT);
            }
            if (endYRange != i) {
                points[startXRange][i].commandAvailableSet.add(Command.RIGHT);
                points[endXRange][i].commandAvailableSet.add(Command.RIGHT);
            }
            if (i != startYRange && i != endYRange) {
                points[startXRange][i].commandAvailableSet.remove(Command.DOWN);
                points[endXRange][i].commandAvailableSet.remove(Command.UP);
            }
        }
        for (int i = startXRange + 1; i < endXRange; i++) {
            for (int j = startYRange + 1; j < endYRange; j++) {
                points[i][j] = points[i][j] == null ? new Point(i, j) : points[i][j];
                points[i][j].visited = true;
            }
        }
    }

    enum Command {
        UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);
        private final int x;
        private final int y;

        Command(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
