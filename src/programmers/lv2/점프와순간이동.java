package programmers.lv2;

public class 점프와순간이동 {
    public int solution(int n) {
        int ans = 0;

        while (n > 0) {
            ans += n % 2;
            n = n / 2;
        }
        return ans;
    }
}
