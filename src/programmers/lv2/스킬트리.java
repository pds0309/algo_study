package programmers.lv2;

import java.util.Arrays;

public class 스킬트리 {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        System.out.println(solution(skill, skill_trees));
    }

    public static int solution(String skill, String[] skill_trees) {
        String regex = "[^" + skill + "]";
        return (int) Arrays.stream(skill_trees)
                .filter(tree -> skill.startsWith(tree.replaceAll(regex, "")))
                .count();
    }
}
