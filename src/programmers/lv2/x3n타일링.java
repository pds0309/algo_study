package programmers.lv2;

public class x3n타일링 {
    public static void main(String[] args) {
        System.out.println(6 % 4);
        System.out.println(2 % 4);
        System.out.println(solution(5000));
    }

    public static int solution(int n) {
        final int divider = 1_000_000_007;
        if (n % 2 == 1) {
            return 0;
        }
        long[] array = new long[n / 2 + 1];
        array[1] = 3;
        array[2] = 11;
        for (int i = 3; i < array.length; i++) {
            array[i] = ((array[i - 1] * 4) - array[i - 2]) % divider;
        }
        return (int) ((array[n / 2] + divider) % divider);
    }
}
