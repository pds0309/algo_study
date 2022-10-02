package programmers.lv2;

import java.util.Arrays;

public class 이진변환반복하기 {
    public static void main(String[] args) {
        String s = "110010101001";
        System.out.println(Arrays.toString(solution(s)));
    }

    private static final int[] answer = {0, 0};

    public static int[] solution(String s) {
        while (!"1".equals(s)) {
            s = filterString(s);
            answer[0]++;
            s = convertNum(s);
        }
        return answer;
    }

    private static String filterString(String s) {
        String result = s.replaceAll("0", "");
        answer[1] += (s.length() - result.length());
        return result;
    }

    private static String convertNum(String s) {
        return Integer.toBinaryString(s.length());
    }
}
