package programmers.lv2;

import java.util.Arrays;

public class K진수에서소수구하기 {

    public static void main(String[] args) {
        int n = 524287;
        int k = 2;
        System.out.println(solution(n, k));
    }

    public static int solution(int n, int k) {
        String number = toJinBub(n, k);
        long[] numArray = Arrays.stream(number.split("0"))
                .filter(val -> !"".equals(val))
                .mapToLong(Long::parseLong)
                .sorted()
                .toArray();
        return (int) Arrays.stream(numArray).filter(val -> isPrime(val)).count();
    }

    private static String toJinBub(int n, int k) {
        StringBuilder stringBuilder = new StringBuilder();
        while (n != 0) {
            stringBuilder.insert(0, n % k);
            n = n / k;
        }
        return stringBuilder.toString();
    }

    public static boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
