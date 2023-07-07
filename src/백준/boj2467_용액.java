package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj2467_용액 {
    static int[] answers = {0, 2000000000};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        int[] arr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        loop(arr);
        System.out.println(answers[0] + " " + answers[1]);
    }

    static void loop(int[] arr) {
        int startIdx = 0;
        int endIdx = arr.length - 1;
        while (startIdx < endIdx) {
            int resultIdx = search(arr, startIdx + 1, endIdx, -arr[startIdx]);
            if (resultIdx == startIdx) {
                startIdx++;
                continue;
            }
            if (arr[resultIdx] + arr[startIdx] == 0) {
                answers[0] = arr[startIdx];
                answers[1] = arr[resultIdx];
                return;
            }
            if (Math.abs(answers[1] + answers[0]) > Math.abs(arr[resultIdx] + arr[startIdx])) {
                answers[0] = arr[startIdx];
                answers[1] = arr[resultIdx];
            }
            startIdx++;
        }
    }

    static int search(int[] arr, int start, int end, int searchValue) {
        int tempEnd = end;
        int tempStart = start;
        while (start <= end) {
            int mid = (end + start) / 2;
            if (arr[mid] == searchValue) {
                return mid;
            }
            if (arr[mid] < searchValue) {
                start = mid + 1;
                tempStart = mid;
                continue;
            }
            end = mid - 1;
            tempEnd = mid;
        }
        if(Math.abs(-searchValue + arr[tempStart]) > Math.abs(-searchValue + arr[tempEnd])) {
            return tempEnd;
        }
        return tempStart;
    }
}
