package programmers.lv2;

import java.util.Arrays;

public class 최댓값과최소값 {
    public String solution(String s) {
        int[] answerArray =
                Arrays.stream(Arrays.stream(s.split(" "))
                        .mapToInt(Integer::parseInt).toArray())
                        .sorted()
                        .toArray();
        return answerArray[0] + " " + answerArray[answerArray.length - 1];
    }
}
