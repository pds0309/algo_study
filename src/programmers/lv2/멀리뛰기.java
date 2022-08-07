package programmers.lv2;

public class 멀리뛰기 {
    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    public static long solution(int n) {
        long[] array = new long[2001];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = (array[i - 1] + array[i - 2]) % 1234567;
        }
        return array[n];
    }
}
