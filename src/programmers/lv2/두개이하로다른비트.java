package programmers.lv2;

import java.util.Arrays;

public class 두개이하로다른비트 {
    public static void main(String[] args) {
        long[] numbers = {2, 7};
        System.out.println(Arrays.toString(solution(numbers)));
    }

    public static long[] solution(long[] numbers) {
        return Arrays.stream(numbers).map(value -> convert(toBinary(value))).toArray();
    }

    private static String toBinary(long number) {
        return "0" + Long.toBinaryString(number) + "1";
    }

    private static long convert(String binaryNumber) {
        int minZeroIdx = binaryNumber.lastIndexOf("0");
        char[] binaryArray = binaryNumber.toCharArray();
        binaryArray[minZeroIdx] = '1';
        binaryArray[minZeroIdx + 1] = '0';
        return Long.parseLong(String.valueOf(binaryArray), 2) >> 1;
    }
}
