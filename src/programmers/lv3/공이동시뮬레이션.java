package programmers.lv3;

import java.util.HashMap;
import java.util.Map;

public class 공이동시뮬레이션 {

    public static void main(String[] args) {
        int[][] queries = {{3, 1}, {2, 2}, {1, 1}, {2, 3}, {0, 1}, {2, 1}};
        int[][] queries2 = {{2, 1}, {0, 1}, {1, 1}, {0, 1}, {2, 1}};
        int[][] queries3 = {{3, 1}, {3, 4}, {3, 5}, {3, 7}};
        int n = 2;
        int m = 5;
        int x = 0;
        int y = 1;
        System.out.println(solution(n, m, x, y, queries));
        System.out.println(solution(2, 2, 0, 0, queries2));
    }

    public static long solution(int n, int m, int x, int y, int[][] queries) {
        QueryInfo queryInfo = new QueryInfo(queries, n, m, x, y);
        queryInfo.reverseTrack();
        return queryInfo.getEnableQueryCount();
    }

    static class QueryInfo {
        long minCol;
        long maxCol;
        long minRow;
        long maxRow;
        int colBound;
        int rowBound;
        int[][] queries;
        boolean invalid;

        Map<Long, Command> commandMap = new HashMap<>();

        public QueryInfo(int[][] queries, int n, int m, int x, int y) {
            this.queries = queries;
            this.minCol = y;
            this.maxCol = y;
            this.minRow = x;
            this.maxRow = x;
            this.colBound = m;
            this.rowBound = n;
            this.invalid = false;
            commandMap.put(0L, Command.COL_DOWN);
            commandMap.put(1L, Command.COL_UP);
            commandMap.put(2L, Command.ROW_DOWN);
            commandMap.put(3L, Command.ROW_UP);
        }

        public void reverseTrack() {
            for (int i = queries.length - 1; i >= 0; i--) {
                long query = queries[i][0];
                long value = queries[i][1];
                commandMap.get(query).operate(value, this);
                if (!validBound()) {
                    this.invalid = true;
                    return;
                }
            }
        }

        private boolean validBound() {
            return this.maxCol >= 0 && this.minCol < this.colBound && this.minRow < this.rowBound && this.maxRow >= 0;
        }

        public long getEnableQueryCount() {
            return this.invalid ? 0 : (this.maxCol - this.minCol + 1) * (this.maxRow - this.minRow + 1);
        }
    }

    enum Command implements QueryOperator {
        ROW_UP((value, queryInfo) -> {
            queryInfo.maxRow -= maxValueConflictedByMaxBound(queryInfo.maxRow, queryInfo.rowBound - 1) ? 0 : value;
            queryInfo.minRow = Math.max(0, queryInfo.minRow - value);
        }),
        ROW_DOWN((value, queryInfo) -> {
            queryInfo.minRow += minValueConflictedByMinBound(queryInfo.minRow) ? 0 : value;
            queryInfo.maxRow = Math.min(queryInfo.rowBound - 1L, queryInfo.maxRow + value);
        }),
        COL_UP((value, queryInfo) -> {
            queryInfo.maxCol -= maxValueConflictedByMaxBound(queryInfo.maxCol, queryInfo.colBound - 1) ? 0 : value;
            queryInfo.minCol = Math.max(0, queryInfo.minCol - value);
        }),
        COL_DOWN((value, queryInfo) -> {
            queryInfo.minCol += minValueConflictedByMinBound(queryInfo.minCol) ? 0 : value;
            queryInfo.maxCol = Math.min(queryInfo.colBound - 1L, queryInfo.maxCol + value);
        });

        private final QueryOperator operator;

        Command(QueryOperator queryOperator) {
            this.operator = queryOperator;
        }

        @Override
        public void operate(long value, QueryInfo queryInfo) {
            operator.operate(value, queryInfo);
        }

        private static boolean minValueConflictedByMinBound(long point) {
            return point == 0;
        }

        private static boolean maxValueConflictedByMaxBound(long point, int maxBound) {
            return point == maxBound;
        }
    }

    interface QueryOperator {
        void operate(long value, QueryInfo queryInfo);
    }
}