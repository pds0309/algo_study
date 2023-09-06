package 백준.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class boj14888_연산자_끼워넣기 {
    static int minAnswer = Integer.MAX_VALUE;
    static int maxAnswer = Integer.MIN_VALUE;
    static int[] numArr;
    static List<Operation> operationList;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bufferedReader.readLine());
        numArr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] operatorArr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        operationList = new ArrayList<>();
        for (int j = 0; j < operatorArr.length; j++) {
            for (int i = 0; i < operatorArr[j]; i++) {
                operationList.add(Operation.values()[j]);
            }
        }
        boolean[] visited = new boolean[operationList.size()];
        doTrack(1, visited, numArr[0]);
        System.out.println(maxAnswer);
        System.out.println(minAnswer);
    }

    static void doTrack(int numIdx, boolean[] visited, int curVal) {
        if (numIdx == numArr.length) {
            maxAnswer = Math.max(maxAnswer, curVal);
            minAnswer = Math.min(minAnswer, curVal);
            return;
        }
        for (int i = 0; i < operationList.size(); i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            doTrack(numIdx + 1, visited, operationList.get(i).operate(curVal, numArr[numIdx]));
            visited[i] = false;
        }
    }

    enum Operation implements Operator {
        PLUS((x, y) -> x + y), MINUS((x, y) -> x - y), MULTIPLE((x, y) -> x * y), DIVIDE((x, y) -> x / y);

        final Operator operator;

        Operation(Operator operator) {
            this.operator = operator;
        }

        @Override
        public int operate(int x, int y) {
            return operator.operate(x, y);
        }

    }

    @FunctionalInterface
    interface Operator {
        int operate(int x, int y);
    }
}