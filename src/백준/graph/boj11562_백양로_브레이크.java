package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj11562_백양로_브레이크 {
    static int[][] arr;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infoArr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int nodeNum = infoArr[0];
        int caseNum = infoArr[1];
        arr = new int[nodeNum + 1][nodeNum + 1];
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                arr[i][j] = 9999999;
            }
        }
        for (int i = 0; i < caseNum; i++) {
            int[] nodeArr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = nodeArr[0];
            int end = nodeArr[1];
            arr[start][end] = 0;
            arr[end][start] = nodeArr[2] == 1 ? 0 : 1;
        }
        trav();
        int requestNum = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < requestNum; i++) {
            int[] request = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (request[0] == request[1]) {
                stringBuilder.append(0).append("\n");
                continue;
            }
            stringBuilder.append(arr[request[0]][request[1]]).append("\n");
        }
        System.out.println(stringBuilder);
    }

    static void trav() {
        for (int startNode = 1; startNode < arr.length; startNode++) {
            for (int i = 1; i < arr.length; i++) {
                for (int j = 1; j < arr.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    arr[i][j] = Math.min(arr[i][j], arr[i][startNode] + arr[startNode][j]);
                }
            }
        }
    }
}
