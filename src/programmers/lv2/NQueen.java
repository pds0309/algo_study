package programmers.lv2;

public class NQueen {
    private static int answer = 0;

    public static int solution(int n) {
        int[] map = new int[n];
        putQueen(0, n, map);
        return answer;
    }

    private static void putQueen(int index, int n, int[] map) {
        if (index == n) {
            answer++;
            return;
        }
        for (int i = 0; i < n; i++) {
            map[index] = i;
            if (checkQueenCanBePlaced(map, index)) {
                putQueen(index + 1, n, map);
            }
        }
    }

    private static boolean checkQueenCanBePlaced(int[] map, int index) {
        for (int i = 0; i < index; i++) {
            if (map[i] == map[index]) {
                return false;
            }
            if (isDiagonal(i, index, map)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDiagonal(int i, int j, int[] array) {
        return Math.abs(array[i] - array[j]) == Math.abs(i - j);
    }

}
