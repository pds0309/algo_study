package programmers.lv3;

import java.util.*;
import java.util.stream.Collectors;

public class 양과늑대 {
    static int maxSheepNumber = 0;
    static LinkedList<Integer>[] adjList;

    public static void main(String[] args) {
        int[] info = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0};
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}};
        System.out.println(solution(info, edges));
    }

    public static int solution(int[] info, int[][] edges) {

        adjList = new LinkedList[info.length];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new LinkedList<>();
        }

        for (int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            adjList[v1].add(v2);
        }
        dfs(info, 0, 0, 0, Collections.singletonList(0));
        return maxSheepNumber;
    }

    private static void dfs(int[] info, int startIndex, int currentSheep, int currentWolf, List<Integer> nextSearchList) {
        if (isSheep(info, startIndex)) {
            currentSheep++;
        } else {
            currentWolf++;
        }

        maxSheepNumber = Math.max(maxSheepNumber, currentSheep);
        if (currentWolf >= currentSheep) {
            return;
        }

        List<Integer> list = nextSearchList.stream()
                .filter(integer -> integer != startIndex)
                .collect(Collectors.toList());
        list.addAll(adjList[startIndex]);

        for (int adj : list) {
            dfs(info, adj, currentSheep, currentWolf, list);
        }
    }

    private static boolean isSheep(int[] info, int index) {
        if (info[index] == 0) {
            return true;
        }
        return false;
    }
}
