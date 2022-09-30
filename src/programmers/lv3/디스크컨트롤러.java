package programmers.lv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 디스크컨트롤러 {
    public static void main(String[] args) {
        int[][] jobs = {{24, 10}, {28, 39}, {43, 20}, {37, 5}, {47, 22}, {20, 47}, {15, 2}, {15, 34}, {35, 43}, {26, 1}};
        int[][] jobs2 = {{0, 1}, {1, 2}, {500, 6}};
        System.out.println(solution(jobs));
    }

    // [0]: 시점
    // [1]: 소요시간
    public static int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        List<Integer> answerList = new ArrayList<>();

        int currentIndex = 0;
        int currentTime = 0;
        int recentEndTime = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        while (true) {
            if (currentIndex == jobs.length && pq.isEmpty()) {
                break;
            }
            if (pq.isEmpty()) {
                pq.offer(jobs[currentIndex++]);
                currentTime = Math.max(jobs[currentIndex][0], recentEndTime);
                continue;
            }
            while (currentIndex != jobs.length && currentTime >= jobs[currentIndex][0]) {
                pq.offer(jobs[currentIndex++]);
            }
            int[] current = pq.poll();
            currentTime = currentTime + current[1];
            recentEndTime = currentTime;
            answerList.add(currentTime - current[0]);
        }

        return answerList.stream().reduce(Integer::sum).orElse(-1) / jobs.length;
    }
}
