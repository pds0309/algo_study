package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj2660_회장뽑기 {
    static int[] answerList;
    static int INF = Integer.MAX_VALUE / 2 - 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int nodeNum = Integer.parseInt(bufferedReader.readLine());
        answerList = new int[nodeNum + 1];
        int[][] arr = new int[nodeNum + 1][nodeNum + 1];
        initArr(arr);
        while (true) {
            int[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (infos[0] == -1) {
                break;
            }
            arr[infos[0]][infos[1]] = 1;
            arr[infos[1]][infos[0]] = 1;
        }
        trav(arr);
        int minValue = setAnswerList(arr);
        int cnt = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < answerList.length; i++) {
            if (answerList[i] == minValue) {
                cnt++;
                stringBuilder.append(i).append(" ");
            }
        }
        System.out.println(minValue + " " + cnt);
        System.out.println(stringBuilder);
    }

    static int setAnswerList(int[][] arr) {
        int minValue = INF;
        for (int i = 1; i < arr.length; i++) {
            answerList[i] = Arrays.stream(arr[i]).filter(value -> value != INF).max().getAsInt();
            minValue = Math.min(minValue, answerList[i]);
        }
        return minValue;
    }

    static void trav(int[][] arr) {
        for (int startNode = 1; startNode < arr.length; startNode++) {
            for (int i = 1; i < arr.length; i++) {
                for (int j = 1; j < arr.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (arr[startNode][j] != INF && arr[i][startNode] != INF) {
                        arr[i][j] = Math.min(arr[i][j], arr[startNode][j] + arr[i][startNode]);
                    }
                }
            }
        }
    }

    static void initArr(int[][] arr) {
        for (int[] ints : arr) {
            Arrays.fill(ints, INF);
        }
    }

}
