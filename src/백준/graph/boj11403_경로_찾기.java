package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj11403_경로_찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bufferedReader.readLine());
        int[][] array = new int[len][len];
        for (int i = 0; i < len; i++) {
            array[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int startIndex = 0; startIndex < len; startIndex++) {
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (array[i][j] == 1) {
                        continue;
                    }
                    if (array[i][startIndex] == 1 && array[startIndex][j] == 1) {
                        array[i][j] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
