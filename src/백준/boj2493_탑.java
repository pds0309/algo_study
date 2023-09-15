package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class boj2493_탑 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        int[] arr = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Deque<Top> deque = new ArrayDeque<>();
        int[] answers = new int[arr.length];
        deque.offerLast(new Top(0, arr[0]));
        for (int i = 1; i < arr.length; i++) {
            while (!deque.isEmpty()) {
                if (arr[i] < deque.peekLast().num) {
                    answers[i] = deque.peekLast().idx + 1;
                    break;
                }
                deque.pollLast();
            }
            deque.offerLast(new Top(i, arr[i]));
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int answer : answers) {
            stringBuilder.append(answer).append(" ");
        }
        System.out.println(stringBuilder);
    }

    static class Top {
        int idx;
        int num;

        public Top(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
}
