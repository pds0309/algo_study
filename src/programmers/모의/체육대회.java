package programmers.모의;

import java.util.ArrayList;
import java.util.List;

public class 체육대회 {
    public static void main(String[] args) {
        int[][] ability = {{40, 10, 10}, {20, 5, 0}, {30, 30, 30}, {70, 0, 70}, {100, 100, 100}};
        System.out.println(solution(ability));
    }

    static int len;
    static int answer;

    public static int solution(int[][] ability) {
        len = ability[0].length;
        Student[] students = new Student[ability.length];
        for (int i = 0; i < ability.length; i++) {
            students[i] = new Student();
            for (int j = 0; j < ability[0].length; j++) {
                students[i].sports.add(ability[i][j]);
            }
        }
        dfs(students, 0, 0);
        return answer;
    }

    private static void dfs(Student[] students, int count, int sum) {
        if (count == len) {
            answer = Math.max(answer, sum);
            return;
        }
        for (Student student : students) {
            if (!student.visited) {
                student.visited = true;
                dfs(students, count + 1, sum + student.sports.get(count));
                student.visited = false;
            }
        }
    }

    static class Student {
        boolean visited = false;
        List<Integer> sports = new ArrayList<>();
    }
}
