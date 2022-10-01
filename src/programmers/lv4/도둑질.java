package programmers.lv4;

public class 도둑질 {
    public static void main(String[] args) {
        int[] money = {1, 2, 3, 15, 1, 1};
        System.out.println(solution(money));
    }

    // {1, 2, 3, 15, 1, 1}
    public static int solution(int[] money) {
        int[] startAtFirstList = new int[money.length];
        int[] startAtSecondList = new int[money.length];
        startAtFirstList[0] = money[0];
        startAtFirstList[1] = money[0];
        startAtFirstList[2] = money[0] + money[2];
        startAtSecondList[1] = money[1];
        startAtSecondList[2] = Math.max(money[1], money[2]);
        for (int i = 3; i < money.length; i++) {
            startAtFirstList[i] = Math.max(money[i - 1] + startAtFirstList[i - 3], startAtFirstList[i - 2] + money[i]);
            startAtSecondList[i] = Math.max(money[i - 1] + startAtSecondList[i - 3], startAtSecondList[i - 2] + money[i]);
        }
        return Math.max(startAtFirstList[money.length - 2], startAtSecondList[money.length - 1]);
    }
}
