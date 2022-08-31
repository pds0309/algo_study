package programmers.lv2;

import java.util.Arrays;

public class 카펫 {
    public static void main(String[] args) {
        int brown = 24;
        int yellow = 24;
        System.out.println(Arrays.toString(solution(brown, yellow)));
    }

    public static int[] solution(int brown, int yellow) {
        double all = brown + yellow;
        int start = (int) (all / 3);

        while (true) {
            if (all / start == ((int) all / start)) {
                if ((start - 2) * (all / start - 2) == yellow) {
                    return new int[]{start, (int) (all / start)};
                }
            }
            start--;
        }
    }
}
