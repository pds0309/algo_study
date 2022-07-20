package programmers.lv2;

import java.util.ArrayList;
import java.util.List;

public class 하노이의탑 {

    static List<Integer> startLocationList = new ArrayList<>();
    static List<Integer> endLocationList = new ArrayList<>();

    public static void main(String[] args) {
        int n = 2;
        System.out.println(solution(n));
   }

    public static int[][] solution(int n) {
        hanoi(n, 1, 3, 2);
        int[][] answer = new int[startLocationList.size()][2];

        for (int i = 0; i < answer.length; i ++) {
            answer[i][0] = startLocationList.get(i);
            answer[i][1] = endLocationList.get(i);
        }

        return answer;
    }

    private static void hanoi(int n, int start, int end, int mid) {
        if (n == 1) {
            startLocationList.add(start);
            endLocationList.add(end);
            return;
        }
        hanoi(n - 1, start, mid, end);
        startLocationList.add(start);
        endLocationList.add(end);
        hanoi(n - 1, mid, end, start);
    }

}
