package programmers.lv3;

public class 최고의집합 {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        if (n > s) {
            return new int[]{-1};
        }
        for (int i = n; i > 0; i--) {
            answer[i - 1] = getCeilMok(s, i);
            s -= answer[i - 1];
        }
        return answer;
    }

    private int getCeilMok(int number, int divider) {
        int result = number / divider;
        return number % divider != 0 ? result + 1 : result;
    }
}
