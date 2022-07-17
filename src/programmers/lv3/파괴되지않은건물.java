package programmers.lv3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 파괴되지않은건물 {

    private static Map<Integer, Integer> activationMap = new HashMap<>();

    public static void main(String[] args) {
        int[][] board = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] skill = {{1, 1, 1, 2, 2, 4}, {1, 0, 0, 1, 1, 2}, {2, 2, 0, 2, 0, 100}};
        System.out.println(solution(board, skill));
    }

    public static int solution(int[][] board, int[][] skill) {
        activationMap.put(1, -1);
        activationMap.put(2, 1);
        int[][] activationArray = new int[board.length + 1][board[0].length + 1];

        for (int[] item : skill) {
            setActivationArray(item, activationArray);
        }
        setCumulativeSumOfActivationArray(activationArray);
        setBoard(activationArray, board);
        return (int) Arrays.stream(board).flatMapToInt(ints -> Arrays.stream(ints).filter(value -> value > 0)).count();
    }

    private static void setActivationArray(int[] skill, int[][] activationArray) {
        int activationQuantity = activationMap.get(skill[0]) * skill[5];
        activationArray[skill[1]][skill[2]] += activationQuantity;
        activationArray[skill[1]][skill[4] + 1] += -activationQuantity;
        activationArray[skill[3] + 1][skill[2]] += -activationQuantity;
        activationArray[skill[3] + 1][skill[4] + 1] += activationQuantity;
    }

    private static void setCumulativeSumOfActivationArray(int[][] activationArray) {
        for (int i = 1; i < activationArray[0].length; i++) {
            activationArray[0][i] += activationArray[0][i - 1];
        }
        for (int i = 1; i < activationArray.length; i++) {
            activationArray[i][0] += activationArray[i - 1][0];
        }
        for (int i = 1; i < activationArray.length; i++) {
            for (int j = 1; j < activationArray[i].length; j++) {
                activationArray[i][j] = activationArray[i][j] + activationArray[i][j - 1] + activationArray[i - 1][j] - activationArray[i - 1][j - 1];
            }
        }
    }

    private static void setBoard(int[][] activationArray, int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] += activationArray[i][j];
            }
        }
    }
}

