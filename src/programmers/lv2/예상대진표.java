package programmers.lv2;

public class 예상대진표 {
    public static int solution(int n, int a, int b) {
        int count = 1;
        a += a % 2;
        b += b % 2;
        while (true) {
            if (a / 2 + a % 2 == b / 2 + b % 2) {
                return count;
            }
            a = a / 2 + a % 2;
            b = b / 2 + b % 2;
            count++;
        }
    }
}