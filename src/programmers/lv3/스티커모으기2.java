package programmers.lv3;

import java.util.Arrays;

public class 스티커모으기2 {
    public static void main(String[] args) {
        int[] sticker = {14, 6, 5, 11, 3, 9, 2, 10};
        System.out.println(solution(sticker));
    }

    public static int solution(int sticker[]) {
        if (sticker.length <= 2) {
            return Arrays.stream(sticker).max().orElse(-1);
        }

        int[] pickFirstArray = new int[sticker.length];
        pickFirstArray[0] = sticker[0];
        pickFirstArray[1] = sticker[0];

        int[] pickSecondArray = new int[sticker.length];
        pickSecondArray[0] = 0;
        pickSecondArray[1] = sticker[1];

        for (int i = 2; i < sticker.length; i++) {
            pickFirstArray[i] = Math.max(sticker[i] + pickFirstArray[i - 2], pickFirstArray[i - 1]);
            pickSecondArray[i] = Math.max(sticker[i] + pickSecondArray[i - 2], pickSecondArray[i - 1]);
        }

        return Math.max(getMaxValueFromArray(Arrays.copyOfRange(pickFirstArray, 0, pickFirstArray.length - 1))
                , getMaxValueFromArray(pickSecondArray));
    }

    private static int getMaxValueFromArray(int[] array) {
        return Arrays.stream(array).max().orElse(-1);
    }
}
