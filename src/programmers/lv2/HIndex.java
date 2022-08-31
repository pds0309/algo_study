package programmers.lv2;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HIndex {
    public static int solution(int[] citations) {
        return IntStream.range(0, Arrays.stream(citations)
                        .max()
                        .orElse(1))
                .filter(val -> Arrays.stream(citations)
                        .filter(va -> va >= val)
                        .count() >= val)
                .max()
                .orElse(0);
    }
}
