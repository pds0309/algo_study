package programmers.lv2;

public class 숫자의표현 {
    public static void main(String[] args) {
        int n = 15;
        System.out.println(solution(n));
    }

    public static int solution(int n) {
        int answer = 1;
        int start = 1;
        int bound = n % 2 == 1 ? n / 2 + 1 : n / 2;
        while (start < bound) {
            int sum = 0;
            for (int i = start; i <= bound; i++) {
                sum += i;
                if (sum == n) {
                    answer++;
                    break;
                }
                if (sum > n) {
                    break;
                }
            }
            start++;
        }
        return answer;
    }
}
