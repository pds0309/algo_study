package programmers.lv2;

import java.util.ArrayDeque;
import java.util.Deque;

public class 짝지어제거하기 {
    public static void main(String[] args) {
        String s = "cdcd";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        Deque<Character> queue = new ArrayDeque<>();
        queue.add(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (!queue.isEmpty() && s.charAt(i) == queue.peekLast()) {
                queue.pollLast();
                continue;
            }
            queue.offerLast(s.charAt(i));
        }
        return queue.isEmpty() ? 1 : 0;
    }
}
