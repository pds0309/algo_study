package programmers.lv2;

import java.util.ArrayDeque;
import java.util.Queue;

public class 두큐합같만들기2 {
    public static void main(String[] args) {
        int[] queue1 = {1, 2, 1, 2};
        int[] queue2 = {1, 10, 1, 2};
        System.out.println(solution(queue1, queue2));
    }

    public static int solution(int[] queue1, int[] queue2) {
        long sumOf1 = 0L;
        long sumOf2 = 0L;
        History his1 = new History();
        History his2 = new History();
        for (int i = 0; i < queue1.length; i++) {
            sumOf1 += queue1[i];
            sumOf2 += queue2[i];
        }
        his1.queue = queue1;
        his2.queue = queue2;
        his1.sum = sumOf1;
        his2.sum = sumOf2;
        if ((sumOf1 + sumOf2) % 2 == 1) {
            return -1;
        }
        int count = 0;
        while (true) {
            if (count != 0 && (his1.startIdx == queue1.length && his2.startIdx == queue2.length)) {
                return -1;
            }
            if (his1.sum == his2.sum) {
                return count;
            }
            History minHis = his1.sum - his2.sum > 0 ? his2 : his1;
            History maxHis = minHis == his1 ? his2 : his1;
            int switchValue = maxHis.getValue();
            minHis.setValue(switchValue);
            minHis.sum += switchValue;
            maxHis.sum -= switchValue;
            count++;
        }
    }

    static class History {
        int[] queue;
        long sum;
        int startIdx;
        Queue<Integer> rest = new ArrayDeque<>();

        public int getValue() {
            if (startIdx >= queue.length) {
                return rest.poll();
            }
            return queue[startIdx++];
        }

        public void setValue(int value) {
            rest.offer(value);
        }
    }
}