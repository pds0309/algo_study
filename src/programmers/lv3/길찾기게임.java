package programmers.lv3;

import java.util.*;

public class 길찾기게임 {
    public static void main(String[] args) {
        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        System.out.println(Arrays.toString(solution(nodeinfo)));
    }

    private static List<Integer>[] resultArray;
    private static Map<int[], Integer> valueMap = new HashMap<>();

    public static int[][] solution(int[][] nodeinfo) {
        resultArray = new List[2];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = new ArrayList<>();
        }

        for (int i = 0; i < nodeinfo.length; i++) {
            valueMap.put(nodeinfo[i], i + 1);
        }

        Arrays.sort(nodeinfo, (o1, o2) -> o2[1] == o1[1] ? o1[0] - o2[0] : o2[1] - o1[1]);
        Tree tree = new Tree();
        for (int[] ints : nodeinfo) {
            tree.add(ints);
        }
        preOrder(tree.root);
        postOrder(tree.root);

        return Arrays.stream(resultArray)
                .map(list -> list.stream()
                        .mapToInt(value -> value)
                        .toArray())
                .toArray(int[][]::new);
    }

    private static void preOrder(Node root) {
        if (root == null) {
            return;
        }
        resultArray[0].add(valueMap.get(root.point));
        preOrder(root.left);
        preOrder(root.right);
    }

    private static void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        resultArray[1].add(valueMap.get(root.point));
    }

    static class Node {
        int[] point;
        Node left;
        Node right;

        Node(int[] point) {
            this.point = point;
        }
    }

    static class Tree {
        Node root;

        public void add(int[] point) {
            if (root == null) {
                root = new Node(point);
                return;
            }
            add(point, root);
        }

        private void add(int[] point, Node root) {
            if (point[0] < root.point[0]) {
                if (root.left != null) {
                    add(point, root.left);
                    return;
                }
                root.left = new Node(point);
            } else {
                if (root.right != null) {
                    add(point, root.right);
                    return;
                }
                root.right = new Node(point);
            }
        }
    }

}
