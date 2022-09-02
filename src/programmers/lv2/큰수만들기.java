package programmers.lv2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class 큰수만들기 {
    public static String solution(String number, int k) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : number.toCharArray()) {
            while (!deque.isEmpty() && deque.peekLast() < c && k > 0) {
                deque.pollLast();
                k--;
            }
            deque.addLast(c);
        }
        StringBuilder stringBuilder = new StringBuilder();
        new ArrayList<>(deque).subList(0, deque.size() - k).forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
