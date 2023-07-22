package 백준.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class boj1068_트리 {

    static int answer = 0;

    /**
     * 3
     * -1 0 1
     * 1
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int[] arr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int delNodeNum = Integer.parseInt(scanner.nextLine());

        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(i);
        }
        int rootIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                rootIdx = i;
                continue;
            }
            if (i != delNodeNum) {
                nodes[arr[i]].nexts.add(i);
            }
        }
        nodes[delNodeNum].isDeleted = true;
        dfs(rootIdx, nodes);
        System.out.println(answer);
    }

    static void dfs(int start, Node[] nodes) {
        if (nodes[start].isDeleted) {
            return;
        }
        if (nodes[start].nexts.isEmpty()) {
            answer++;
            return;
        }
        for (int i = 0; i < nodes[start].nexts.size(); i++) {
            dfs(nodes[start].nexts.get(i), nodes);
        }
    }

    static class Node {
        int idx;
        List<Integer> nexts;
        boolean isDeleted;

        public Node(int idx) {
            this.idx = idx;
            this.nexts = new ArrayList<>();
        }
    }
}
