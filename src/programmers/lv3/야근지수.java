package programmers.lv3;

import java.util.PriorityQueue;

public class 야근지수 {
    public static void main(String[] args) {
        int[] works = {1, 1};
        int n = 3;
        System.out.println(solution(n, works));
    }

    public static long solution(int n, int[] works) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int work : works) {
            priorityQueue.offer(work);
        }
        for (int i = 0; i < n; i++) {
            if (priorityQueue.peek() <= 0) {
                return 0;
            }
            priorityQueue.offer(priorityQueue.poll() - 1);
        }
        long answer = 0;
        while (!priorityQueue.isEmpty()) {
            answer += priorityQueue.peek() * priorityQueue.poll();
        }
        return answer;
    }

}
