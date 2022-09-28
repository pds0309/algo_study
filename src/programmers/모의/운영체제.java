package programmers.모의;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 운영체제 {
    public static void main(String[] args) {
        int[][] program = {{3, 6, 4}, {4, 2, 5}, {1, 0, 5}, {5, 0, 5}};
        int[][] program2 = {{2, 0, 10}, {1, 5, 5}, {3, 5, 3}, {3, 12, 2}};
        int[][] pro3 = {{1, 0, 10}, {2, 1, 10}, {2, 2, 10}};
        System.out.println(Arrays.toString(solution(pro3)));
    }

    public static long[] solution(int[][] program) {
        Program[] programs = new Program[program.length];
        for (int i = 0; i < programs.length; i++) {
            programs[i] = new Program(program[i], i);
        }
        Arrays.sort(programs, (o1, o2) -> o1.startTime == o2.startTime ? o1.priority - o2.priority : o1.startTime - o2.startTime);
        PriorityQueue<Program> pq = new PriorityQueue<>((o1, o2) -> o1.priority == o2.priority ? o1.startTime - o2.startTime : o1.priority - o2.priority);

        int currentIdx = 0;
        long currentTime = 0;
        long[] answerArray = new long[11];
        long lastEndTime = 0;
        while (true) {
            if (currentIdx == programs.length && pq.isEmpty()) {
                answerArray[0] = currentTime;
                break;
            }
            if (pq.isEmpty()) {
                Program pro = programs[currentIdx++];
                pq.offer(pro);
                currentTime = Math.max(lastEndTime, pro.startTime);
                continue;
            }
            while (currentIdx != programs.length && currentTime >= programs[currentIdx].startTime) {
                Program pro = programs[currentIdx++];
                pq.offer(pro);
            }
            Program pro = pq.poll();
            currentTime = currentTime + pro.duration;
            lastEndTime = currentTime;
            pro.score = currentTime - pro.duration - pro.startTime;
        }
        for (Program value : programs) {
            answerArray[value.priority] += value.score;
        }
        return answerArray;
    }

    static class Program {
        int priority;
        int startTime;
        int duration;
        int idx;

        long score = 0L;

        public Program(int[] program, int idx) {
            this.priority = program[0];
            this.startTime = program[1];
            this.duration = program[2];
            this.idx = idx;
        }
    }
}

