package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class boj1374_강의실 {
    static int answer = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int caseNum = Integer.parseInt(bufferedReader.readLine());
        Lecture[] lectures = new Lecture[caseNum];
        for (int i = 0; i < caseNum; i++) {
            int[] lectureInfos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            lectures[i] = new Lecture(lectureInfos[1], lectureInfos[2]);
        }
        Arrays.sort(lectures);
        PriorityQueue<Lecture> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(lectures[0]);
        int idx = 1;
        while (idx < caseNum) {
            Lecture prev = priorityQueue.peek();
            if (lectures[idx].start >= prev.end) {
                priorityQueue.poll();
            }
            priorityQueue.add(lectures[idx++]);
            answer = Math.max(answer, priorityQueue.size());
        }
        System.out.println(answer);
    }

    static class Lecture implements Comparable<Lecture> {
        int start;
        int end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            int endInfo = this.end - o.end;
            if (endInfo == 0) {
                return this.start - o.start;
            }
            return endInfo;
        }
    }
}
