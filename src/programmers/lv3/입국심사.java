package programmers.lv3;

import java.util.Arrays;

public class 입국심사 {
    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
        System.out.println(solution(n, times));
    }

    public static long solution(int n, int[] times) {
        Arrays.sort(times);
        long start = 0;
        long end = (long) n * times[0];
        while (start < end) {
            long mid = (start + end) / 2;
            if (sumOfArrayWithDividedValue(times, mid) < n) {
                start = mid + 1;
                continue;
            }
            end = mid;
        }
        return start;
    }

    private static long sumOfArrayWithDividedValue(int[] array, long number) {
        return Arrays.stream(array).mapToLong(v -> number / v).sum();
    }
}
