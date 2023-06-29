package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1956_운동 {
    static int INF = Integer.MAX_VALUE / 2 - 1;
    static int answer = INF;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infoArr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int vilNum = infoArr[0];
        int roadNum = infoArr[1];
        int[][] array = new int[vilNum + 1][vilNum + 1];
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                if (i == j) {
                    continue;
                }
                array[i][j] = INF;
            }
        }
        for (int i = 0; i < roadNum; i++) {
            int[] vilRoadInfos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            array[vilRoadInfos[0]][vilRoadInfos[1]] = vilRoadInfos[2];
        }
        for (int startIndex = 1; startIndex < array.length; startIndex++) {
            for (int i = 1; i < array.length; i++) {
                for (int j = 1; j < array.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    array[i][j] = Math.min(array[i][j], array[i][startIndex] + array[startIndex][j]);
                }
            }
        }
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                if (i == j) {
                    continue;
                }
                if (array[i][j] != INF && array[j][i] != INF) {
                    answer = Math.min(answer, array[i][j] + array[j][i]);
                }
            }
        }
        System.out.println(answer == INF ? -1 : answer);
    }
}
