package 백준.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj5639_이진검색트리 {

    private static final StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = bufferedReader.readLine();
        if (isLastLine(line)) {
            return;
        }
        Node root = new Node(Integer.parseInt(line));
        while (true) {
            String currentLine = bufferedReader.readLine();
            if(isLastLine(currentLine)) {
                break;
            }
            root.put(Integer.parseInt(currentLine));
        }
        postOrder(root);
        System.out.println(stringBuilder);
    }

    private static void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        stringBuilder.append(root.current).append("\n");
    }

    private static boolean isLastLine(String str) {
        return str == null || str.isBlank();
    }

    private static class Node {
        int current;
        Node left;
        Node right;

        public Node(int current) {
            this.current = current;
        }

        public void put(int number) {
            if (number < current) {
                if (this.left == null) {
                    this.left = new Node(number);
                    return;
                }
                this.left.put(number);
            } else {
                if (this.right == null) {
                    this.right = new Node(number);
                    return;
                }
                this.right.put(number);
            }
        }

    }

}
