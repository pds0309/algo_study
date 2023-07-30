package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj2217_로프 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bufferedReader.readLine());
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < len; i++) {
            priorityQueue.add(Integer.parseInt(bufferedReader.readLine()));
        }
        int ans = 0;
        while (!priorityQueue.isEmpty()) {
            ans = Math.max(ans, priorityQueue.size() * priorityQueue.poll());
        }
        System.out.println(ans);
    }
}
