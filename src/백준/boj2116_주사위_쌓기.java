package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

public class boj2116_주사위_쌓기 {
    static Map<Integer, Integer> map = Map.of(0, 5, 1, 3, 2, 4, 3, 1, 4, 2, 5, 0);
    static int maxValue = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bufferedReader.readLine());
        String[] dices = new String[len];
        for (int i = 0; i < len; i++) {
            dices[i] = bufferedReader.readLine().replaceAll(" ", "");
        }
        for (int i = 1; i <= 6; i++) {
            int pickedIndex = dices[0].indexOf(i + "");
            String pickedValue = dices[0].charAt(pickedIndex) + "";
            dfs(pickedValue, dices, 0, 0);
        }
        System.out.println(maxValue);
    }

    static void dfs(String pickedValue, String[] dices, int idx, int sum) {
        if (idx == dices.length) {
            maxValue = Math.max(maxValue, sum);
            return;
        }
        int pickedValueIndx = dices[idx].indexOf(pickedValue);
        String pickedLowerValue = dices[idx].charAt(map.get(pickedValueIndx)) + "";
        String pickedDice = dices[idx].replace(pickedValue, "").replace(pickedLowerValue, "");
        String max = Arrays.stream(pickedDice.split(""))
                .max(String::compareTo)
                .get();
        dfs(pickedLowerValue, dices, idx + 1, sum + Integer.parseInt(max));
    }
}
