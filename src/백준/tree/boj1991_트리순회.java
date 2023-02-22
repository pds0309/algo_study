package 백준.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class boj1991_트리순회 {

    private static class Node {
        char current;
        Node left;
        Node right;

        public Node(char current) {
            this.current = current;
        }

    }

    private static final Map<Character, Node> nodeMap = new HashMap<>();
    private static final StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numOfNode = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < numOfNode; i++) {
            char[] chars = bufferedReader.readLine().replace(" ", "").toCharArray();
            nodeMap.putIfAbsent(chars[0], new Node(chars[0]));
            Node current = nodeMap.get(chars[0]);
            if (isWord(chars[1])) {
                Node left = new Node(chars[1]);
                current.left = left;
                putNodeMap(chars[1], left);
            }
            if (isWord(chars[2])) {
                Node right = new Node(chars[2]);
                current.right = right;
                putNodeMap(chars[2], right);
            }
        }
        preOrder(nodeMap.get('A'));
        stringBuilder.append("\n");
        inOrder(nodeMap.get('A'));
        stringBuilder.append("\n");
        postOrder(nodeMap.get('A'));
        System.out.println(stringBuilder);
    }

    private static void preOrder(Node root) {
        if (isCurrentNodeNull(root)) {
            return;
        }
        stringBuilder.append(root.current);
        preOrder(root.left);
        preOrder(root.right);
    }

    private static void inOrder(Node root) {
        if (isCurrentNodeNull(root)) {
            return;
        }
        inOrder(root.left);
        stringBuilder.append(root.current);
        inOrder(root.right);
    }

    private static void postOrder(Node root) {
        if (isCurrentNodeNull(root)) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        stringBuilder.append(root.current);
    }

    private static boolean isWord(char c) {
        return Character.isAlphabetic(c);
    }

    private static void putNodeMap(char c, Node node) {
        nodeMap.putIfAbsent(c, node);
    }

    private static boolean isCurrentNodeNull(Node node) {
        return node == null;
    }

}
