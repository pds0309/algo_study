package programmers.모의;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 유전법칙 {
    public static void main(String[] args) {
        int[][] queries = {{3, 1}, {2, 3}, {3, 9}};
        System.out.println(Arrays.toString(solution(queries)));
    }

    public static String[] solution(int[][] queries) {
        List<String> answerList = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            if (queries[i][0] == 1) {
                answerList.add("Rr");
                continue;
            }
            int n = queries[i][0] - 1;
            int v = queries[i][1];

            while (true) {
                double value = Math.pow(4, n);
                if (n == 1) {
                    answerList.add(getValue(4, v));
                    break;
                }
                if (v / value <= 0.25) {
                    answerList.add("RR");
                    break;
                }
                if (v / value >= 0.75) {
                    answerList.add("rr");
                    break;
                }
                n--;
                v %= 4;
            }
        }
        return answerList.toArray(new String[0]);
    }

    public static String getValue(int n, int p) {
        if (p % n == 1) {
            return "RR";
        }
        if (p % n == 0) {
            return "rr";
        }
        return "Rr";
    }
}
