package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1043_거짓말 {
    static People[] peopleArr;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] truthInfos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int peopleNum = infos[0];
        int partyNum = infos[1];
        peopleArr = new People[peopleNum + 1];
        for (int i = 1; i < peopleArr.length; i++) {
            peopleArr[i] = new People(i, false);
        }
        for (int i = 1; i < truthInfos.length; i++) {
            peopleArr[truthInfos[i]].hasTruth = true;
        }
        int[][] parties = new int[partyNum][];
        for (int i = 0; i < partyNum; i++) {
            parties[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j < parties[i].length - 1; j++) {
                if (find(parties[i][j]) == find(parties[i][j + 1])) {
                    continue;
                }
                union(parties[i][j], parties[i][j + 1]);
            }
        }
        int answer = partyNum;
        for (int i = 0; i < partyNum; i++) {
            for (int j = 1; j < parties[i].length; j++) {
                if (peopleArr[find(parties[i][j])].hasTruth) {
                    answer--;
                    break;
                }
            }
        }
        System.out.println(answer);
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        }
        if (peopleArr[x].hasTruth || peopleArr[y].hasTruth) {
            peopleArr[x].hasTruth = true;
            peopleArr[y].hasTruth = true;
        }
        peopleArr[y].parent = x;
        return true;
    }

    static int find(int x) {
        if (peopleArr[x].parent == x) {
            return x;
        }
        return peopleArr[x].parent = find(peopleArr[x].parent);
    }

    static class People {
        int parent;
        boolean hasTruth;

        public People(int parent, boolean hasTruth) {
            this.parent = parent;
            this.hasTruth = hasTruth;
        }
    }
}
