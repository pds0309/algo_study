package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 쿼드압축개수세기 {
    public static void main(String[] args) {
        int[][] arr = {{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}};
        System.out.println(Arrays.toString(solution(arr)));
    }

    private static final int[] countArray = new int[]{0, 0};

    public static int[] solution(int[][] arr) {
        List<Integer> list = Arrays.stream(arr)
                .flatMapToInt(Arrays::stream)
                .boxed()
                .collect(Collectors.toList());
        jaegui(list);
        return countArray;
    }

    private static void jaegui(List<Integer> list) {
        if (list.stream().distinct().count() == 1) {
            countArray[list.get(0)]++;
            return;
        }
        int half = (int) Math.sqrt(list.size());
        List<Integer> quaterList1 = new ArrayList<>();
        List<Integer> quaterList2 = new ArrayList<>();
        List<Integer> quaterList3 = new ArrayList<>();
        List<Integer> quaterList4 = new ArrayList<>();
        for (int j = 0; j < list.size() / 2; j += half) {
            quaterList1.addAll(list.subList(j, j + half / 2));
            quaterList2.addAll(list.subList(j + half / 2, j + half));
            quaterList3.addAll(list.subList(j + (list.size() / 2), j + (list.size() / 2) + half / 2));
            quaterList4.addAll(list.subList(j + (list.size() / 2) + half / 2, j + (list.size() / 2) + half));
        }
        jaegui(quaterList1);
        jaegui(quaterList2);
        jaegui(quaterList3);
        jaegui(quaterList4);
    }
}