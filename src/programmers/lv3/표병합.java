import java.util.*;

public class Solution {
    static Table table = new Table();
    static List<String> answerList = new ArrayList<>();

    public static String[] solution(String[] commands) {
        for (String cmd : commands) {
            String[] splitCmd = cmd.split(" ");
            Command.valueOf(splitCmd[0]).operate(splitCmd);
//            printArr(cmd);
        }
        return answerList.toArray(String[]::new);
    }

    static class Table {
        Cell[][] cells = new Cell[51][51];

        public Table() {
            for (int i = 1; i < cells.length; i++) {
                for (int j = 1; j < cells[1].length; j++) {
                    this.cells[i][j] = new Cell(i, j);
                }
            }
        }

        public void updateAllByValue(String target, String result) {
            for (int i = 1; i < cells.length; i++) {
                for (int j = 1; j < cells[0].length; j++) {
                    if (cells[i][j].value.equals(target)) {
                        cells[i][j].value = result;
                    }
                }
            }
        }

        public Cell getCellByPoint(String x, String y) {
            return this.cells[Integer.parseInt(x)][Integer.parseInt(y)];
        }
    }

    static class Cell {
        int x;
        int y;
        String value = "EMPTY";
        Set<Cell> mergedSet = new HashSet<>();

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
            mergedSet.add(this);
        }

        public void merge(Cell cell) {
            if (this.equals(cell)) {
                return;
            }
            String val = findValueWithCombination(cell);
            this.value = val;
            Set<Cell> combinedSet = new HashSet<>(this.mergedSet);
            combinedSet.addAll(cell.mergedSet);
            combinedSet.forEach(c -> {
                c.value = val;
                c.mergedSet = combinedSet;
            });
            this.mergedSet = combinedSet;
            cell.mergedSet = combinedSet;
        }

        public void unmerge() {
            String value = this.value;
            Set<Cell> modifySet = new HashSet<>(this.mergedSet);
            modifySet.forEach(cell -> {
                cell.value = "EMPTY";
                cell.clearMergeSet();
            });
            this.value = value;
        }

        public void update(String value) {
            mergedSet.forEach(cell -> cell.value = value);
        }

        private String findValueWithCombination(Cell cell) {
            if (this.value.equals("EMPTY")) {
                return cell.value;
            }
            return this.value;
        }

        private void clearMergeSet() {
            this.mergedSet = new HashSet<>();
            this.mergedSet.add(this);
        }

        @Override
        public boolean equals(Object cell) {
            return this.x == ((Cell) cell).x && this.y == ((Cell) cell).y;
        }
    }

    enum Command implements CommandOperator {
        UPDATE(commands -> {
            if (commands.length == 4) {
                table.getCellByPoint(commands[1], commands[2]).update(commands[3]);
                return;
            }
            table.updateAllByValue(commands[1], commands[2]);
        }), MERGE(commands -> {
            Cell fromCell = table.getCellByPoint(commands[1], commands[2]);
            fromCell.merge(table.getCellByPoint(commands[3], commands[4]));
        }), UNMERGE(commands -> {
            Cell cell = table.getCellByPoint(commands[1], commands[2]);
            cell.unmerge();
        }), PRINT(commands -> {
            Cell cell = table.getCellByPoint(commands[1], commands[2]);
            answerList.add(cell.value);
        });

        private final CommandOperator commandOperator;

        Command(CommandOperator commandOperator) {
            this.commandOperator = commandOperator;
        }

        @Override
        public void operate(String[] commands) {
            this.commandOperator.operate(commands);
        }
    }

    interface CommandOperator {
        public void operate(String[] commands);
    }

    static void printArr(String command) {
        System.out.println("##########: " + command);
        for (int i = 1; i < table.cells.length; i++) {
            for (int j = 1; j < table.cells.length; j++) {
                System.out.print(table.cells[i][j].value.equals("EMPTY") ? "X " : table.cells[i][j].value + " ");
            }
            System.out.println();
        }
    }
}
