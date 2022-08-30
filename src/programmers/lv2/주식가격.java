package programmers.lv2;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 주식가격 {
    public static void main(String[] args) {
        int[] prices = {4, 2, 3, 2, 3};
        System.out.println(Arrays.toString(solution(prices)));
    }

    public static int[] solution(int[] prices) {
        int[] result = new int[prices.length];
        for (int i = 0; i < result.length; i++) {
            final int I = i;
            result[i] =
                    IntStream.range(i + 1, result.length)
                            .filter(idx -> prices[idx] < prices[I])
                            .findFirst()
                            .orElse(result.length - 1) - I;
        }
        return result;
    }
}
