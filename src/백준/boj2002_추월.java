package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class boj2002_추월 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(bufferedReader.readLine());
        Queue<String> entered = new ArrayDeque<>();
        Queue<String> leaved = new ArrayDeque<>();
        for (int i = 0; i < num; i++) {
            entered.add(bufferedReader.readLine());
        }
        for (int i = 0; i < num; i++) {
            leaved.add(bufferedReader.readLine());
        }
        Set<String> overtaken = new HashSet<>();
        while (!entered.isEmpty()) {
            String current = entered.peek();
            if (overtaken.contains(current)) {
                entered.poll();
                continue;
            }
            while (!current.equals(leaved.peek())) {
                overtaken.add(leaved.poll());
            }
            entered.poll();
            leaved.poll();
        }
        System.out.println(overtaken.size());
    }
}
