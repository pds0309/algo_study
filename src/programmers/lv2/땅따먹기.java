package programmers.lv2;

import java.util.Arrays;

public class 땅따먹기 {

    public static void main(String[] args) {
        int[][] land = {{4, 3, 2, 1}, {2, 2, 2, 1}, {6, 6, 6, 4}, {8, 7, 6, 5}};
        System.out.println(solution(land));
    }


    public static int solution(int[][] land) {

        for (int i = 1; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                land[i][j] += getArrayMaxValueWithProhibitedIndex(land[i - 1], j);
            }
        }
        return Arrays.stream(land[land.length - 1]).max().orElse(-1);
    }

    private static int getArrayMaxValueWithProhibitedIndex(int[] array, int prohibitedIndex) {
        int maxValue = -1;
        for (int i = 0; i < array.length; i++) {
            if (i != prohibitedIndex) {
                maxValue = Math.max(array[i], maxValue);
            }
        }
        return maxValue;
    }

}
