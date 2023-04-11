package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class boj1987_알파벳 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] xy = strToIntArr(bufferedReader.readLine());
        char[][] array = new char[xy[0]][];
        Set<Character> travelSet = new HashSet<>();
        for (int i = 0; i < xy[0]; i++) {
            array[i] = bufferedReader.readLine().toCharArray();
        }
        travelSet.add(array[0][0]);
        dfs(0, 0, array, travelSet, 0);
        System.out.println(answer + 1);
    }

    static int answer = 0;

    private static void dfs(int x, int y, char[][] array, Set<Character> travelSet, int count) {
        answer = Math.max(answer, count);
        if (x + 1 < array.length && !travelSet.contains(array[x + 1][y])) {
            travelSet.add(array[x + 1][y]);
            dfs(x + 1, y, array, travelSet, count + 1);
        }
        if (x - 1 >= 0 && !travelSet.contains(array[x - 1][y])) {
            travelSet.add(array[x - 1][y]);
            dfs(x - 1, y, array, travelSet, count + 1);
        }
        if (y - 1 >= 0 && !travelSet.contains(array[x][y - 1])) {
            travelSet.add(array[x][y - 1]);
            dfs(x, y - 1, array, travelSet, count + 1);
        }
        if (y + 1 < array[0].length && !travelSet.contains(array[x][y + 1])) {
            travelSet.add(array[x][y + 1]);
            dfs(x, y + 1, array, travelSet, count + 1);
        }
        travelSet.remove(array[x][y]);
    }

    private static int[] strToIntArr(String str) {
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
