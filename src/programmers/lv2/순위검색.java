package programmers.lv2;

import java.util.*;

public class 순위검색 {
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        System.out.println(Arrays.toString(solution(info, query)));
    }

    static Map<String, Score> map = new HashMap<>();

    public static int[] solution(String[] info, String[] query) {

        for (String value : info) {
            pe(value.split(" "), 0, "");
        }
        map.values().forEach(Score::sort);

        Query[] queries = Arrays.stream(query)
                .map(s -> new Query(s.replaceAll(" and", "")))
                .toArray(Query[]::new);

        int[] answer = new int[query.length];
        for (int i = 0; i < queries.length; i++) {
            if (map.containsKey(queries[i].query)) {
                Score score = map.get(queries[i].query);
                answer[i] = score.list.size() - searchIndexOfGreaterValue(score.list, queries[i].score);
            }
        }
        return answer;
    }

    private static void pe(String[] info, int count, String current) {
        if (count == info.length - 1) {
            map.computeIfAbsent(current, s -> new Score());
            map.get(current).add(Integer.parseInt(info[4]));
            return;
        }
        pe(info, count + 1, current + "-");
        pe(info, count + 1, current + info[count]);
    }

    static class Score {
        List<Integer> list = new ArrayList<>();

        public void sort() {
            Collections.sort(list);
        }

        public void add(int num) {
            list.add(num);
        }
    }

    static class Query {
        String query;
        int score;

        public Query(String query) {
            this.query = query.substring(0, query.lastIndexOf(" ")).replaceAll(" ", "");
            this.score = Integer.parseInt(query.substring(query.lastIndexOf(" ") + 1));
        }
    }

    private static int searchIndexOfGreaterValue(List<Integer> list, int value) {
        int start = 0;
        int end = list.size();
        while (start < end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < value) {
                start = mid + 1;
                continue;
            }
            end = mid;
        }
        return start;
    }
}
