package programmers.lv2;

import java.util.Queue;
import java.util.LinkedList;

public class 다리를지나는트럭 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<Integer> queue = new LinkedList<>();
        int len = truck_weights.length;
        int weightSum = 0;

        for (int i = 0; i < len; i++) {
            if (weightSum + truck_weights[i] <= weight) {
                answer++;
                queue.offer(truck_weights[i]);
                weightSum += truck_weights[i];
            } else {
                while (queue.size() < bridge_length) {
                    queue.offer(0);
                    answer++;
                }
                weightSum -= queue.poll();
                i--;
            }
        }
        answer += bridge_length;
        return answer;
    }
}