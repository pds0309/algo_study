package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class boj2143_두배열의합 {
    static Map<Integer, Integer> mapA = new HashMap<>();
    static Map<Integer, Integer> mapB = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int answer = Integer.parseInt(bufferedReader.readLine());
        bufferedReader.readLine();
        int[] arrA = strToIntArr(bufferedReader.readLine());
        bufferedReader.readLine();
        int[] arrB = strToIntArr(bufferedReader.readLine());
        setSubArraySum(arrA, mapA);
        setSubArraySum(arrB, mapB);
        long sum = 0;
        for (Map.Entry<Integer, Integer> entry : mapA.entrySet()) {
            sum += entry.getValue().longValue() * mapB.getOrDefault(answer - entry.getKey(), 0).longValue();
        }
        System.out.println(sum);
    }

    private static void setSubArraySum(int[] arr, Map<Integer, Integer> map) {
        for (int i = 0; i < arr.length; i++) {
            int start = arr[i];
            map.put(start, map.get(start) == null ? 1 : map.get(start) + 1);
            for (int j = i + 1; j < arr.length; j++) {
                map.put(start + arr[j], map.get(start + arr[j]) == null ? 1 : map.get(start + arr[j]) + 1);
                start += arr[j];
            }
        }
    }

    private static int[] strToIntArr(String str) {
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
