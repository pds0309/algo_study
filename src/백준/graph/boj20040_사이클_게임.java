package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj20040_사이클_게임 {
    static int[] parents = new int[500000];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int nodeNum = Integer.parseInt(stringTokenizer.nextToken(" "));
        int caseNum = Integer.parseInt(stringTokenizer.nextToken(" "));
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < caseNum; i++) {
            int[] nodes = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (union(nodes[0], nodes[1])) {
                System.out.println(i + 1);
                return;
            }
        }
        System.out.println(0);
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return true;
        }
        if(x <= y) {
            parents[y] = x;
            return false;
        }
        parents[x] = y;
        return false;
    }

    static int find(int node) {
        if (parents[node] == node) {
            return node;
        }
        parents[node] = find(parents[node]);
        return parents[node];
    }
}
