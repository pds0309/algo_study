package programmers.lv2;

public class 이n타일링 {
    public int solution(int n) {
        int[] array = new int[n + 1];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = (array[i - 1] + array[i - 2]) % 1_000_000_007;
        }
        return array[n];
    }
}
