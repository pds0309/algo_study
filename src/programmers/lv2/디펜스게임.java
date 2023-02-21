package programmers.lv2;

import java.util.PriorityQueue;

public class 디펜스게임 {
    /**
     * @param n:    병사수 <= 1_000_000_000
     * @param k:    무적 <= 500_000
     * @param enemy <= 적 수 <= 1_000_000
     * @return 막은 라운드 수
     */
    public static int solution(int n, int k, int[] enemy) {
        if(k >= enemy.length) {
            return enemy.length;
        }
        return doDefence(n, enemy, k);
    }

    private static int doDefence(int remainSoldiers, int[] enemy, int remainProtections) {
        int count = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < enemy.length; i++) {
            if (defeated(remainSoldiers)) {
                if (!protectable(remainProtections)) {
                    return count - 1;
                }
                if (!priorityQueue.isEmpty()) {
                    remainSoldiers += priorityQueue.poll();
                    i--;
                }
                remainProtections--;
                continue;
            }
            priorityQueue.offer(enemy[i]);
            remainSoldiers -= enemy[i];
            if(defeated(remainSoldiers) && !protectable(remainProtections) && enemy.length - 1 == i) {
                continue;
            }
            count++;
        }
        return count;
    }

    private static boolean defeated(int remainSoldiers) {
        return remainSoldiers < 0;
    }

    private static boolean protectable(int remainProtections) {
        return remainProtections > 0;
    }

}

}
