package programmers.lv2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 위장 {
    public static int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        Arrays.stream(clothes).forEach(strings -> {
            map.compute(strings[1], (s, integer) -> integer == null ? 1 : integer + 1);
        });
        return map.values().stream()
                .reduce((n1, n2) -> (n1 + 1) * (n2 + 1) - 1)
                .orElse(1);
    }
}
