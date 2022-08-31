package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 프렌즈4블록 {
    public static void main(String[] args) {
        String[] board = {"AAAAA", "AUUUA", "AUUAA", "AAAAA"};
        int m = 4;
        int n = 5;
        System.out.println(solution(m, n, board));
    }

    public static int solution(int m, int n, String[] board) {
        Board bdObj = new Board(board, m, n);
        while (bdObj.deletedState) {
            bdObj.setDeletedState(false);
            bdObj.doDeletePoint();
            bdObj.pullDeletedPoint();
        }
        return bdObj.findDisabledPointCount();
    }

    static class Board {
        private final List<Point>[] board;
        private boolean deletedState;

        public Board(String[] bd, int m, int n) {
            this.board = new List[n];
            this.deletedState = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (this.board[i] == null) {
                        this.board[i] = new ArrayList<>();
                    }
                    this.board[i].add(new Point(bd[j].charAt(i)));
                }
            }
        }

        public void setDeletedState(boolean deletedState) {
            this.deletedState = deletedState;
        }

        public void pullDeletedPoint() {
            for (int i = 0; i < board.length; i++) {
                board[i] =
                        Stream.concat(board[i].stream()
                                                .filter(point -> point.deleted)
                                                .map(Point::changeCharacter),
                                        board[i].stream()
                                                .filter(point -> !point.deleted))
                                .collect(Collectors.toList());
            }
        }

        public void doDeletePoint() {
            for (int i = 0; i < board.length - 1; i++) {
                for (int j = 0; j < board[i].size() - 1; j++) {
                    if (board[i].get(j).getCharacter() == '0') {
                        continue;
                    }
                    if (board[i].get(j).equals(board[i].get(j + 1))
                            && board[i].get(j).equals(board[i + 1].get(j))
                            && board[i].get(j).equals(board[i + 1].get(j + 1))) {
                        board[i].get(j).setDeleted();
                        board[i].get(j + 1).setDeleted();
                        board[i + 1].get(j).setDeleted();
                        board[i + 1].get(j + 1).setDeleted();
                        this.deletedState = true;
                    }
                }
            }
        }

        public int findDisabledPointCount() {
            return (int) Arrays.stream(board)
                    .flatMap(points -> points.stream()
                            .filter(point -> point.character == '0'))
                    .count();
        }

    }

    static class Point {
        char character;
        boolean deleted;

        public Point(char str) {
            this.character = str;
            this.deleted = false;
        }

        public void setDeleted() {
            this.deleted = true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return character == point.character;
        }

        @Override
        public int hashCode() {
            return Objects.hash(character);
        }

        public Point changeCharacter() {
            this.character = '0';
            return this;
        }

        public char getCharacter() {
            return character;
        }
    }
}
