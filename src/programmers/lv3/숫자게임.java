package programmers.lv3;

import java.util.Arrays;

class 숫자게임 {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int answer = 0;
        int inA = 0;
        int inB = 0;
        for (int i = 0; i < B.length; i++) {
            if (B[inB] <= A[inA]) {
                inB++;
                continue;
            }
            inB++;
            inA++;
            answer++;
        }
        return answer;
    }
}