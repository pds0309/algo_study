package programmers.lv2;

import java.util.*;

public class 줄서는방법 {
    public static void main(String[] args) {
        int n = 3;
        int k = 5;
        System.out.println(Arrays.toString(solution(n, k)));
    }

    private static final List<Integer> answerList = new ArrayList<>();
    private static final LinkedList<Integer> sequentialLine = new LinkedList<>();

    public static int[] solution(int n, long k) {
        for (int i = 0; i <= n; i++) {
            sequentialLine.add(i);
        }
        recursive(n, k);
        int[] answer = new int[n];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    private static void recursive(int n, long k) {
        if (n == 0) {
            return;
        }
        long divider = getFacto(n) / n;
        long remain = k % divider;
        long quotient = k / divider;
        int currentIndex = (int) (remain == 0 ? quotient : quotient + 1);
        answerList.add(sequentialLine.remove(currentIndex));
        recursive(n - 1, remain == 0 ? divider : remain);
    }

    private static long getFacto(int number) {
        if (number <= 1) {
            return 1;
        }
        long[] facto = new long[number + 1];
        facto[0] = 1;
        facto[1] = 1;
        for (int i = 2; i <= number; i++) {
            facto[i] = i * facto[i - 1];
        }
        return facto[number];
    }

}
