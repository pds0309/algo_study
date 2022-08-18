package programmers.lv3;

public class 기지국설치 {
    public static int solution(int n, int[] stations, int w) {
        int start = 1;
        int result = 0;
        int coverLength = w * 2 + 1;
        for (int station : stations) {
            if (station - w - start > 0) {
                result += Math.ceil((double) (station - w - start) / coverLength);
            }
            start = station + w + 1;
        }
        if (n - start + 1 > 0) {
            result += Math.ceil((double) (n - start + 1) / coverLength);
        }
        return result;
    }
}
