package programmers.lv2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 영어끝말잇기 {
    public static void main(String[] args) {
        int n = 3;
        String[] words = {"aba", "aba", "A", "F"};
        System.out.println(Arrays.toString(solution(n, words)));
    }

    public static int[] solution(int n, String[] words) {
        Set<String> set = new HashSet<>();
        for (int i = 1; i < words.length; i++) {
            set.add(words[i - 1]);
            if (has(set, words[i]) || checkNotLinked(words[i - 1], words[i])) {
                return new int[]{i % n + 1, i / n + 1};
            }
        }
        return new int[]{0, 0};
    }

    private static boolean has(Set<String> set, String word) {
        return set.contains(word);
    }

    private static boolean checkNotLinked(String prev, String current) {
        return prev.charAt(prev.length() - 1) != current.charAt(0);
    }
}
