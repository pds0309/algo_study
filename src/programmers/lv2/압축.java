package programmers.lv2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 압축 {
    private static final Map<String, Integer> map = new HashMap<>();
    private static int mapLastIdx = 0;

    public static void main(String[] args) {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";
        System.out.println(solution(msg));
    }

    public static int[] solution(String msg) {
        initMap();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < msg.length(); i++) {
            String currentWord = String.valueOf(msg.charAt(i));
            StringBuilder temp = new StringBuilder(currentWord);
            for (int j = i + 1; j < msg.length(); j++) {
                if (map.get(temp.toString() + msg.charAt(j)) != null) {
                    temp.append(msg.charAt(j));
                    i++;
                } else {
                    currentWord = temp.toString();
                    map.put(currentWord + msg.charAt(j), ++mapLastIdx);
                    break;
                }
                if (j == msg.length() - 1) {
                    currentWord = temp.toString();
                }
            }
            result.add(map.get(currentWord));
        }
        return result.stream().mapToInt(v -> v).toArray();
    }

    private static void initMap() {
        for (char c = 'A'; c <= 'Z'; c++) {
            map.put(String.valueOf(c), ++mapLastIdx);
        }
    }
}
