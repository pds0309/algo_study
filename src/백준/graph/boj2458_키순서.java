package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class boj2458_키순서 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infoArr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int peopleNum = infoArr[0];
        int caseNum = infoArr[1];
        int[][] arr = new int[peopleNum + 1][peopleNum + 1];
        for (int i = 0; i < caseNum; i++) {
            int[] cases = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            arr[cases[0]][cases[1]] = 1;
        }
        for (int startIndex = 1; startIndex < arr.length; startIndex++) {
            for (int i = 1; i < arr.length; i++) {
                for (int j = 1; j < arr.length; j++) {
                    if (i == j || arr[i][j] != 0) {
                        continue;
                    }
                    if (arr[i][startIndex] == 1 && arr[startIndex][j] == 1) {
                        arr[i][j] = 1;
                    }
                }
            }
        }
        int[] rowArr = new int[peopleNum + 1];
        int[] colArr = new int[peopleNum + 1];
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                rowArr[i] += arr[i][j];
                colArr[j] += arr[i][j];
            }
        }
        long result = IntStream.range(1, arr.length).map(i -> rowArr[i] + colArr[i]).filter(v -> v == peopleNum - 1).count();
        System.out.println(result);
    }
}
