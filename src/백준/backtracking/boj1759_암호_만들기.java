package 백준.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1759_암호_만들기 {
    static StringBuilder answerStringBuilder = new StringBuilder();
    static String[] mo = {"a", "e", "i", "o", "u"};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        char[] charArray = bufferedReader.readLine().replace(" ", "").toCharArray();
        Arrays.sort(charArray);
        int len = infos[0];
        pe(len, new StringBuilder(), new boolean[charArray.length], charArray, 0);
        System.out.println(answerStringBuilder);
    }

    static void pe(int len, StringBuilder stringBuilder, boolean[] visited, char[] charArray, int idx) {
        if (stringBuilder.length() == len) {
            int moCnt = 0;
            for (String s : mo) {
                if (stringBuilder.indexOf(s) != -1) {
                    moCnt++;
                }
            }
            if (moCnt >= 1 && stringBuilder.length() - moCnt >= 2) {
                answerStringBuilder.append(stringBuilder).append("\n");
            }
            return;
        }
        for (int i = idx; i < charArray.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            pe(len, stringBuilder.append(charArray[i]), visited, charArray, i);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            visited[i] = false;
        }
    }
}
