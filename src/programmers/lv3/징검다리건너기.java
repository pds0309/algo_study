package programmers.lv3;

import java.util.ArrayDeque;
import java.util.Deque;

public class 징검다리건너기 {
    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        System.out.println(solution(stones, k));
    }

    public static int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < stones.length; i++) {
            while (!deque.isEmpty() && stones[deque.peekLast()] < stones[i]) {
                deque.pollLast();
            }
            record(deque, i, k);
            if (i >= k - 1) {
                answer = Math.min(stones[deque.peekFirst()], answer);
            }
        }
        return answer;
    }

    private static void record(Deque<Integer> deque, int index, int range) {
        deque.offerLast(index);
        if (deque.peekFirst() == index - range) {
            deque.pollFirst();
        }
    }
}
