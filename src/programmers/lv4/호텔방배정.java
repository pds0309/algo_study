package programmers.lv4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 호텔방배정 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(1L, new long[]{1, 3, 4, 1, 3, 1})));
    }

    private static final Map<Long, Long> nodeMap = new HashMap<>();

    public static long[] solution(long k, long[] room_number) {
        long[] answer = Arrays.copyOf(room_number, room_number.length);
        for (int i = 0; i < room_number.length; i++) {
            if (!nodeMap.containsKey(room_number[i])) {
                nodeMap.put(room_number[i], getNext(room_number[i] + 1));
                continue;
            }
            long next = getNext(nodeMap.get(room_number[i]));
            answer[i] = next;
            nodeMap.put(room_number[i], next);
            nodeMap.put(next, getNext(nodeMap.getOrDefault(next, next + 1)));
        }
        return answer;
    }

    public static long getNext(long number) {
        long answer = number;
        while (nodeMap.containsKey(answer)) {
            nodeMap.put(answer, getNext(nodeMap.get(answer)));
            answer = nodeMap.get(answer);
        }
        return answer;
    }

}
