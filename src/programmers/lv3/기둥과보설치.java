package programmers.lv3;

import java.util.*;

public class 기둥과보설치 {
    public static void main(String[] args) {
        int[][] build_frame = {{1, 0, 0, 1}, {1, 1, 1, 1}, {1, 1, 0, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
        int[][] array2 = {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}};
        int n = 5;
        System.out.println(Arrays.toString(solution(n, build_frame)));
    }

    private static Map<String, Structure> structureMap =
            Map.of("00", Structure.DEL_COLUMN, "10", Structure.DEL_FLOOR,
                    "01", Structure.ADD_COLUMN, "11", Structure.ADD_FLOOR);
    // [0,0,0,1]
    // 0,0 에 기둥을 설치한다.: 쿼리 기준 위로

    private static PointState[][] board;

    // return: [1,0,0]
    public static int[][] solution(int n, int[][] build_frame) {
        board = new PointState[n + 2][n + 2];
        setBoard();

        // 시작x 시작y, 기둥여부, 삭제여부
        for (int[] ints : build_frame) {
            int startY = ints[0] + 1;
            int startX = ints[1] + 1;
            Structure structure = structureMap.get(ints[2] + "" + ints[3]);
            structure.changeState(startX, startY);
        }
        List<int[]> answer = new ArrayList<>();
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                if (board[i][j].hasFloor) {
                    answer.add(new int[]{j - 1, i - 1, 1});
                }
                if (board[i][j].hasColumn) {
                    answer.add(new int[]{j - 1, i - 1, 0});
                }
            }
        }
        answer.sort((o1, o2) -> o1[0] == o2[0] ? (o1[1] == o2[1] ? o1[2] - o2[1] : o1[1] - o2[1]) : o1[0] - o2[0]);
        return answer.toArray(value -> new int[][]{});
    }

    private static class PointState {
        boolean hasFloor;
        boolean hasColumn;
        boolean isRoot;

        public PointState(boolean hasFloor, boolean isRoot) {
            this.hasFloor = hasFloor;
            this.hasColumn = false;
            this.isRoot = isRoot;
        }

    }

    // 기둥은 바닥이 있으면 설치할 수 있다.

    // 기둥은 다음과 같으면 삭제할 수 없다.
    //  - 밑또는 위에 기둥이 있는데 양 옆에 보가 없다.
    //  - 좌또는 우에 보가 있는데 그 옆에 끝에 기둥이 없다.

    // 보는 다음과 같으면 설치할 수 있다.
    //  - 해당 자리에 기둥이 있거나 옆에 기둥이 있다.
    //  - 양옆에 보가 있다.

    // 보는 다음과 같으면 삭제할 수 있다.
    //  - 위에 기둥이 있는데 해당 기둥 반대편에 보가 있거나 밑에 기둥이 있다.

    enum Structure implements StructureValidator, StructureCustomizer {

        ADD_COLUMN((x, y) -> {
            if (board[x][y].isRoot) {
                return true;
            }
            return board[x][y].hasFloor || board[x][y - 1].hasFloor
                    || board[x - 1][y].hasColumn;
        }, (x, y) -> {
            board[x][y].hasColumn = true;
        }), ADD_FLOOR((x, y) -> {
            if (board[x - 1][y].hasColumn || board[x - 1][y + 1].hasColumn) {
                return true;
            }
            if (board[x][y - 1].hasFloor && board[x][y + 1].hasFloor) {
                return true;
            }
            return false;
        }, (x, y) -> {
            board[x][y].hasFloor = true;
        }),
        DEL_COLUMN((x, y) -> {
            board[x][y].hasColumn = false;
            boolean result = true;
            if (board[x + 1][y].hasColumn) {
                result = Structure.ADD_COLUMN.validate(x + 1, y);
            }
            if (result && board[x + 1][y - 1].hasFloor) {
                result = Structure.ADD_FLOOR.validate(x + 1, y - 1);
            }
            if (result && board[x + 1][y].hasFloor) {
                result = Structure.ADD_FLOOR.validate(x + 1, y);
            }
            if (result && board[x + 1][y + 1].hasFloor) {
                result = Structure.ADD_FLOOR.validate(x + 1, y + 1);
            }
            board[x][y].hasColumn = true;
            return result;
        }, (x, y) -> {
            board[x][y].hasColumn = false;
        }),
        DEL_FLOOR((x, y) -> {
            board[x][y].hasFloor = false;
            boolean result = true;
            if (board[x][y].hasColumn) {
                result = Structure.ADD_COLUMN.validate(x, y);
            }
            if (result && board[x][y + 1].hasColumn) {
                result = Structure.ADD_COLUMN.validate(x, y + 1);
            }
            if (result && board[x][y + 1].hasFloor) {
                result = Structure.ADD_FLOOR.validate(x, y + 1);
            }
            if (result && board[x][y - 1].hasFloor) {
                result = Structure.ADD_FLOOR.validate(x, y - 1);
            }
            board[x][y].hasFloor = true;
            return result;
        }, (x, y) -> {
            board[x][y].hasFloor = false;
        });

        private final StructureValidator structureValidator;
        private final StructureCustomizer structureCustomizer;

        Structure(StructureValidator structureValidator, StructureCustomizer structureCustomizer) {
            this.structureValidator = structureValidator;
            this.structureCustomizer = structureCustomizer;
        }

        @Override
        public boolean validate(int x, int y) {
            return structureValidator.validate(x, y);
        }

        @Override
        public void customize(int x, int y) {
            this.structureCustomizer.customize(x, y);
        }

        public void changeState(int x, int y) {
            if (validate(x, y)) {
                customize(x, y);
            }
        }
    }

    @FunctionalInterface
    interface StructureValidator {
        boolean validate(int x, int y);
    }

    @FunctionalInterface
    interface StructureCustomizer {
        void customize(int x, int y);
    }

    private static void setBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 1) {
                    board[i][j] = new PointState(false, true);
                    continue;
                }
                board[i][j] = new PointState(false, false);
            }
        }
    }
}
