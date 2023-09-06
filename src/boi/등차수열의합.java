package boi;

public class 등차수열의합 {

    public static void main(String[] args) {
        System.out.println(solution(4, 6));
    }

    public static long solution(int a, int b) {
        long min = Math.min(a, b);
        long max = Math.max(a, b);
        long answer = (min + max) * (max - min + 1) / 2;
        return answer;
    }

}
