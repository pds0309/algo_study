package programmers.lv2;

import java.util.*;
import java.util.stream.Collectors;

public class 메뉴리뉴얼 {
    public static void main(String[] args) {
        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2, 3, 4};
        System.out.println(Arrays.toString(solution(orders, course)));
    }

    static Map<Integer, Map<String, Integer>> mapMap = new HashMap();

    public static String[] solution(String[] orders, int[] course) {
        for (int k : course) {
            mapMap.put(k, new HashMap<>());
        }
        for (String order : orders) {
            for (int j = 0; j < order.length(); j++) {
                if (Arrays.binarySearch(course, j + 1) >= 0) {
                    pe(order, 0, j + 1, "", new boolean[order.length()], 0);
                }
            }
        }
        List<String> answerList = new ArrayList<>();
        for (Map.Entry<Integer, Map<String, Integer>> entry : mapMap.entrySet()) {
            entry.getValue().values().stream()
                    .filter(integer -> integer >= 2)
                    .max(Comparator.naturalOrder())
                    .ifPresent(maxValue ->
                            answerList.addAll(entry.getValue().keySet().stream()
                                    .filter(s -> entry.getValue().get(s) >= maxValue)
                                    .map(String::valueOf)
                                    .collect(Collectors.toList())));
        }
        return answerList.stream().sorted().toArray(String[]::new);
    }

    private static void pe(String str, int count, int maxLen, String current, boolean[] visited, int start) {
        if (count == maxLen) {
            char[] chars = current.toCharArray();
            Arrays.sort(chars);
            put(mapMap.get(maxLen), String.valueOf(chars));
            return;
        }
        for (int i = start; i < str.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                pe(str, count + 1, maxLen, current + str.charAt(i), visited, i + 1);
                visited[i] = false;
            }
        }
    }

    private static void put(Map<String, Integer> map, String key) {
        map.compute(key, (k, v) -> v == null ? 1 : v + 1);
    }
}
