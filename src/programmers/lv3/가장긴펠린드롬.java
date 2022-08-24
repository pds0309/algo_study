package programmers.lv3;

public class 가장긴펠린드롬 {
    public static int solution(String s) {
        String reversedStr = new StringBuilder(s).reverse().toString();
        int answer = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.length() - i - 1 < answer) {
                continue;
            }
            for (int j = i + 1; j < s.length() + 1; j++) {
                String currentStr = s.substring(i, j);
                if (!reversedStr.contains(currentStr)) {
                    break;
                }
                if (isPalin(currentStr)) {
                    answer = Math.max(answer, currentStr.length());
                }
            }
        }
        return answer;
    }

    private static boolean isPalin(String str) {
        return new StringBuilder(str).reverse().toString().equals(str);
    }
}
