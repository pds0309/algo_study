package programmers.lv3;

import java.util.HashSet;
import java.util.Set;

public class 풍선터트리기 {
    public static void main(String[] args) {
        int[] a = {-16, 27, 65, -2, 58, -92, -71, -68, -61, -33};
        System.out.println(solution(a));
    }

    public static int solution(int[] a) {
        int[] bottomToTop = a.clone();
        int[] topToBottom = a.clone();
        Set<Integer> set = new HashSet<>();
        int bound = a.length - 1;
        for (int i = 1; i < bound; i++) {
            bottomToTop[i] = Math.min(bottomToTop[i - 1], bottomToTop[i]);
            topToBottom[bound - i] = Math.min(topToBottom[bound - i + 1], topToBottom[bound - i]);
            set.add(bottomToTop[i]);
            set.add(topToBottom[bound - i]);
        }
        set.add(a[0]);
        set.add(a[bound]);
        return set.size();
    }
}
