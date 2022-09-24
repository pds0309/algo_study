package programmers.lv2;

import java.util.Arrays;

public class 행렬테두리회전하기 {

    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        System.out.println(Arrays.toString(solution(rows, columns, queries)));
    }

    static int[] answer;

    public static int[] solution(int rows, int columns, int[][] queries) {
        answer = new int[queries.length];
        int[][] map = new int[rows][columns];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = (i * map[0].length) + j + 1;
            }
        }
        for (int i = 0; i < queries.length; i++) {
            int startX = queries[i][0] - 1;
            int startY = queries[i][1] - 1;
            int xRange = queries[i][2] - 1;
            int yRange = queries[i][3] - 1;
            int temp = map[startX][startY];
            int min = temp;
            map[startX][startY] = map[startX + 1][startY];
            int tempIndex = startX;
            for (int j = startY + 1; j <= yRange; j++) {
                int temp2 = map[tempIndex][j];
                map[tempIndex][j] = temp;
                temp = temp2;
                min = Math.min(min, temp);
            }
            tempIndex = yRange;
            for (int j = startX + 1; j <= xRange; j++) {
                int temp2 = map[j][tempIndex];
                map[j][tempIndex] = temp;
                temp = temp2;
                min = Math.min(min, temp);
            }
            tempIndex = xRange;
            for (int j = yRange - 1; j >= startY; j--) {
                int temp2 = map[tempIndex][j];
                map[tempIndex][j] = temp;
                temp = temp2;
                min = Math.min(min, temp);
            }
            tempIndex = startY;
            for (int j = xRange - 1; j >= startX; j--) {
                int temp2 = map[j][tempIndex];
                map[j][tempIndex] = temp;
                temp = temp2;
                min = Math.min(min, temp);
            }
            answer[i] = min;
        }
        return answer;
    }


}
