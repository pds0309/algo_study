package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj1715_카드_정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bufferedReader.readLine());
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < len; i++) {
            priorityQueue.add(Integer.parseInt(bufferedReader.readLine()));
        }
        if (len == 1) {
            System.out.println(0);
            return;
        }
        int sum = 0;
        while (priorityQueue.size() != 1) {
            int minSum = priorityQueue.poll() + priorityQueue.poll();
            sum += minSum;
            priorityQueue.add(minSum);
        }
        System.out.println(sum);
    }
}
