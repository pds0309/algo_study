package 백준;

import java.util.Scanner;

public class boj17478_재귀함수가_뭔가요 {
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        int num = Integer.parseInt(new Scanner(System.in).nextLine());
        stringBuilder = new StringBuilder();
        stringBuilder.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.").append("\n");
        rec(0, num);
        System.out.println(stringBuilder);
    }

    static void rec(int cnt, int max) {
        String preStr = "_".repeat(cnt * 4);
        stringBuilder.append(preStr).append("\"재귀함수가 뭔가요?\"").append("\n");
        if (cnt == max) {
            stringBuilder.append(preStr).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"").append("\n");
            stringBuilder.append(preStr).append("라고 답변하였지.").append("\n");
            return;
        }
        stringBuilder.append(preStr).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.").append("\n");
        stringBuilder.append(preStr).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.").append("\n");
        stringBuilder.append(preStr).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"").append("\n");
        rec(cnt + 1, max);
        stringBuilder.append(preStr).append("라고 답변하였지.").append("\n");
    }
}
