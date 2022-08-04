package programmers.lv2;

import java.util.Arrays;

public class 숫자블록 {
    public static void main(String[] args) {
        long begin = 99999999;
        long end = 100000000;
        System.out.println(Arrays.toString(solution(begin, end)));
    }

    public static int[] solution(long begin, long end) {
        int[] answer = new int[(int) ((end - begin) + 1)];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = getNumber((int) (i + begin));
        }
        return answer;
    }

    private static int getNumber(int number) {
        if (number == 1) {
            return 0;
        }
        if (isPrime(number)) {
            return 1;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0 && number / i <= 10000000) {
                return number / i;
            }
        }
        return 1;
    }

    public static boolean isPrime(int n) {
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
