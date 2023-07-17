package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1717_집합의_표현 {
    static int[] parents;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = bufferedReader.readLine().split(" ");
        int nodeNum = Integer.parseInt(strs[0]);
        int caseNum = Integer.parseInt(strs[1]);
        parents = new int[nodeNum + 1];
        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < caseNum; i++) {
            String[] inputs = bufferedReader.readLine().split(" ");
            boolean hasSameParent = find(Integer.parseInt(inputs[1])) == find(Integer.parseInt(inputs[2]));
            if ("1".equals(inputs[0])) {
                stringBuilder.append(hasSameParent ? "YES" : "NO").append("\n");
                continue;
            }
            if (hasSameParent) {
                continue;
            }
            union(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]));
        }
        System.out.println(stringBuilder.deleteCharAt(stringBuilder.length() - 1));
    }

    static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        }
        parents[y] = x;
        return true;
    }
}
