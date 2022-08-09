package programmers.lv3;

import java.util.Arrays;

public class 거스름돈 {
    public static void main(String[] args) {
        int n = 6;
        int[] money = {1, 2, 5};
        System.out.println(solution(n, money));
    }

    public static int solution(int n, int[] money) {
        Arrays.sort(money);
        int[] array = new int[n + 1];
        array[0] = 1;
        for (int k : money) {
            for (int i = 1; i < array.length; i++) {
                if (k > i) {
                    continue;
                }
                array[i] += array[i - k];
            }
        }
        return array[array.length - 1];
    }
}
