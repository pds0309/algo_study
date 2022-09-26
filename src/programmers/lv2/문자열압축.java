package programmers.lv2;

public class 문자열압축 {
    public static void main(String[] args) {
        String s1 = "abcabcabcabcdededededede";
        String s2 = "aabbaccc";
        System.out.println(solution(s2));
    }

    public static int solution(String s) {
        int answer = s.length();
        for (int range = 1; range < s.length(); range++) {
            StringBuilder sb = new StringBuilder(s);
            StringBuilder result = new StringBuilder();
            int startIdx = 0;
            int count = 0;
            while (true) {
                if(startIdx + range > s.length()) {
                    result.insert(0, count <= 1 ? "" : count);
                    result.append(sb.substring(startIdx));
                    break;
                }
                String temp = sb.substring(startIdx, startIdx + range);
                startIdx += range;
                if (result.length() >= temp.length() && result.substring(0, temp.length()).equals(temp)) {
                    count++;
                } else {
                    result.insert(0, count <= 1 ? "" : count);
                    count = 1;
                    result.insert(0, temp);
                }
            }
            answer = Math.min(answer, result.toString().length());
        }
        return answer;
    }
}
