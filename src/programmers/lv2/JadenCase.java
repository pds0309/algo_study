package programmers.lv2;

public class JadenCase {
    public static void main(String[] args) {
        String s = "hello  world";
        System.out.println(solution(s));
    }

    public static String solution(String s) {
        String answer = s.charAt(0) >= 97 ? (char) (s.charAt(0) - 32) + "" : s.charAt(0) + "";
        boolean wasBlank = false;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                wasBlank = true;
                answer += ' ';
                continue;
            }
            if (wasBlank) {
                answer += (s.charAt(i) + "").toUpperCase();
                wasBlank = false;
                continue;
            }
            if (s.charAt(i) >= 65 && s.charAt(i) < 97) {
                answer += (char) (s.charAt(i) + 32);
            } else {
                answer += s.charAt(i) + "";
            }
        }
        return answer;
    }
}
