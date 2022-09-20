package programmers.lv3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class 보석쇼핑 {
    public static void main(String[] args) {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        System.out.println(Arrays.toString(solution(gems)));
    }

    public static int[] solution(String[] gems) {
        int answerSize = (int) Arrays.stream(gems).distinct().count();
        Map<String, Integer> gemMap = new HashMap<>();

        int startIndex = 0;
        int[] answerArray = {1, 1};
        int minRange = Integer.MAX_VALUE;
        gemMap.put(gems[startIndex], 1);

        for (int i = 1; i <= gems.length && startIndex <= i; ) {
            if (gemMap.size() == answerSize) {
                if (minRange > i - startIndex) {
                    answerArray[0] = startIndex + 1;
                    answerArray[1] = i;
                    minRange = i - startIndex;
                }
                remove(gemMap, gems[startIndex++]);
                continue;
            }
            if (i < gems.length) {
                add(gemMap, gems[i]);
            }
            i++;
        }
        return answerArray;
    }

    private static void remove(Map<String, Integer> map, String value) {
        AtomicInteger atomicInteger = new AtomicInteger();
        map.computeIfPresent(value, (key, val) -> {
            atomicInteger.set(val - 1);
            return val - 1;
        });
        if (atomicInteger.get() == 0) {
            map.remove(value);
        }
    }

    private static void add(Map<String, Integer> map, String value) {
        map.putIfAbsent(value, 0);
        map.compute(value, (key, val) -> val + 1);
    }
}
