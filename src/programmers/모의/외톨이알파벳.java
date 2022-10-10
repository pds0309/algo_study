package programmers.모의;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class 외톨이알파벳 {
    public static void main(String[] args) {
        String input_string = "edeaaabbccd";
        System.out.println(solution(input_string));
    }

    public static String solution(String input_string) {
        String str = "X" + input_string + "X";
        Map<Character, StringInfo> map = new HashMap<>();
        char temp = str.charAt(0);
        for (int i = 1; i < str.length() - 1; i++) {
            map.putIfAbsent(str.charAt(i), new StringInfo());
            if (str.charAt(i) != temp) {
                map.get(str.charAt(i)).count++;
            }
            temp = str.charAt(i);
        }
        String result =
                map.entrySet().stream()
                        .filter(characterStringInfoEntry -> characterStringInfoEntry.getValue().alone && characterStringInfoEntry.getValue().count >= 2)
                        .map(characterStringInfoEntry -> String.valueOf(characterStringInfoEntry.getKey()))
                        .sorted()
                        .collect(Collectors.joining());
        return result.equals("") ? "N" : result;
    }

    static class StringInfo {
        int count;
        boolean alone = true;
    }
}
