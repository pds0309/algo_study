package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1613_역사 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infoArray = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int issueNum = infoArray[0];
        int relNum = infoArray[1];
        int[][] array = new int[issueNum + 1][issueNum + 1];
        for (int i = 0; i < relNum; i++) {
            int[] rels = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            array[rels[0]][rels[1]] = 1;
        }
        int findNum = Integer.parseInt(bufferedReader.readLine());
        int[][] findArray = new int[findNum][2];
        for (int i = 0; i < findNum; i++) {
            findArray[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                for (int k = 1; k < array.length; k++) {
                    if (j == k || array[j][k] != 0) {
                        continue;
                    }
                    if (array[i][k] == 1 && array[j][i] == 1) {
                        array[j][k] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < findNum; i++) {
            int x = findArray[i][0];
            int y = findArray[i][1];
            if (array[x][y] == 0 && array[y][x] == 0) {
                System.out.println(0);
                continue;
            }
            if (array[x][y] == 1) {
                System.out.println(-1);
                continue;
            }
            System.out.println(1);
        }
    }
}
