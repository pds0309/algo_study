package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 거리두기확인하기 {
    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}
                , {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        System.out.println(Arrays.toString(solution(places)));
    }
    static int[] answer;

    public static int[] solution(String[][] places) {
        answer = new int[places.length];
        Arrays.fill(answer, 1);
        for (int i = 0; i < places.length; i++) {
            List<Character>[] place = new List[places[i].length + 1];
            for (int j = 0; j < places[i].length; j++) {
                if (place[j] == null) {
                    place[j] = new ArrayList<>();
                }
                place[j].addAll(places[i][j].chars().mapToObj(value -> (char) value).collect(Collectors.toList()));
                place[j].add('X');
            }
            place[place.length - 1] = new ArrayList<>();
            for (int j = 0; j <= places[i][0].length(); j++) {
                place[place.length - 1].add('X');
            }
            checkKeepDistance(place, i);
        }
        return answer;
    }

    public static void checkKeepDistance(List<Character>[] place, int idx) {
        for (int i = 0; i < place.length - 1; i++) {
            for (int j = 0; j < place[i].size() - 1; j++) {
                if (place[i].get(j) != 'P') {
                    continue;
                }
                if (place[i].get(j + 1) == 'P' || place[i + 1].get(j) == 'P') {
                    answer[idx] = 0;
                    return;
                }
                if (place[i].get(j + 1) == 'O' && place[i].get(j + 2) == 'P') {
                    answer[idx] = 0;
                    return;
                }
                if (place[i + 1].get(j) == 'O' && place[i + 2].get(j) == 'P') {
                    answer[idx] = 0;
                    return;
                }
                if ((place[i + 1].get(j) == 'O' || place[i].get(j + 1) == 'O') && place[i + 1].get(j + 1) == 'P') {
                    answer[idx] = 0;
                    return;
                }
                if (i >= 1) {
                    if ((place[i - 1].get(j) == 'O' || place[i].get(j + 1) == 'O') && place[i - 1].get(j + 1) == 'P') {
                        answer[idx] = 0;
                        return;
                    }
                }
            }
        }
    }
}
