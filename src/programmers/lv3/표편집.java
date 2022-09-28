package programmers.lv3;

import java.util.*;
import java.util.stream.Collectors;

public class 표편집 {
    public static void main(String[] args) {
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        String[] cmd2 = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        int n = 8;
        int k = 2;
        System.out.println(solution(n, k, cmd2));
    }

    static Map<Integer, Node> table = new HashMap<>();
    static Deque<Node> removedCache = new ArrayDeque<>();

    /**
     * @param n:   행개수
     * @param k:   시작행위치
     * @param cmd: 쿼리
     * @return
     */
    public static String solution(int n, int k, String[] cmd) {
        setTable(n);
        Node current = table.get(k);
        for (String s : cmd) {
            String[] splitedQuery = (s + " 1").split(" ");
            current = Command.valueOf(splitedQuery[0]).operate(current, Integer.parseInt(splitedQuery[1]));
        }
        return table.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(integerNodeEntry -> integerNodeEntry.getValue().status).collect(Collectors.joining());
    }

    static void setTable(int n) {
        Node top = new Node(0);
        table.put(0, top);
        for (int i = 1; i < n; i++) {
            Node current = new Node(i);
            top.bottom = current;
            current.top = top;
            table.put(i, current);
            top = current;
        }
    }

    static class Node {
        Node top;
        Node bottom;
        String status = "O";
        int idx;

        public Node(int idx) {
            this.idx = idx;
        }
        
        public Node delNode() {
            this.status = "X";
            if (this.top != null) {
                this.top.bottom = this.bottom;
            }
            if (this.bottom != null) {
                this.bottom.top = this.top;
            }
            return this;
        }

        public void rollBack() {
            if (this.top != null) {
                this.top.bottom = this;
            }
            if (this.bottom != null) {
                this.bottom.top = this;
            }
            this.status = "O";
        }
    }

    enum Command implements Operator {
        U((current, val) -> {
            Node temp = current;
            for (int i = 0; i < val; i++) {
                temp = temp.top;
            }
            return temp;
        }), D((current, val) -> {
            Node temp = current;
            for (int i = 0; i < val; i++) {
                temp = temp.bottom;
            }
            return temp;
        }), C((current, val) -> {
            removedCache.offerLast(current.delNode());
            return current.bottom == null ? current.top : current.bottom;
        }), Z((current, val) -> {
            removedCache.pollLast().rollBack();
            return current;
        });

        private final Operator operator;

        Command(Operator operator) {
            this.operator = operator;
        }

        @Override
        public Node operate(Node current, int val) {
            return operator.operate(current, val);
        }
    }

    interface Operator {
        Node operate(Node current, int val);
    }
}
