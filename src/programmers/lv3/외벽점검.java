package programmers.lv3;
import java.util.*;
import java.util.stream.Collectors;

public class 외벽점검 {
    public static void main(String[] args) {
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        int[] weak2 = {1, 3, 4, 9, 10};
        int[] dist2 = {3, 5, 7};
        System.out.println(solution(200, new int[]{0, 10, 50, 80, 120, 160}, new int[]{1, 10, 5, 40, 30}));
    }

    static int answer = Integer.MAX_VALUE;
    static List<int[]> distList = new ArrayList<>();

    public static int solution(int n, int[] weak, int[] dist) {
        int[] weaks = getWeaks(weak, n);
        distPe(dist, new boolean[dist.length], 0, new ArrayDeque<>());
        for (int[] currentDists : distList) {
            findCoveredWeakNum(currentDists, weaks, weak.length);
        }
        if (answer == Integer.MAX_VALUE) {
            return -1;
        }
        return answer;
    }

    private static void findCoveredWeakNum(int[] dists, int[] weak, int length) {
        Queue<Integer> distQueue = Arrays.stream(dists).boxed().collect(Collectors.toCollection(ArrayDeque::new));
        for (int i = 0; i < length; i++) {
            int startWeak = weak[i];
            Queue<Integer> distQ = new ArrayDeque<>(distQueue);
            int currentDist = distQ.poll();
            int count = 1;
            for (int j = i + 1; j < i + length; j++) {
                int nextWeak = weak[j];
                if (nextWeak - startWeak <= currentDist) {
                    count++;
                    continue;
                }
                startWeak = nextWeak;
                if (distQ.isEmpty()) {
                    break;
                }
                currentDist = distQ.poll();
                count++;
            }
            if (count == length) {
                answer = Math.min(answer, distQueue.size() - distQ.size());
            }
        }
    }

    private static void distPe(int[] dist, boolean[] visited, int count, Deque<Integer> deque) {
        if (count == dist.length) {
            distList.add(deque.stream().mapToInt(value -> value).toArray());
            return;
        }
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                deque.offerLast(dist[i]);
                distPe(dist, visited, count + 1, deque);
                deque.pollLast();
                visited[i] = false;
            }
        }
    }

    private static int[] getWeaks(int[] weak, int n) {
        int[] weaks = new int[weak.length * 2 - 1];
        System.arraycopy(weak, 0, weaks, 0, weak.length);
        for (int i = weak.length; i < weaks.length; i++) {
            weaks[i] = weak[i - weak.length] + n;
        }
        return weaks;
    }
}