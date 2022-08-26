package programmers.lv2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class 캐시 {
    public static void main(String[] args) {
        int cacheSize = 0;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution(cacheSize, cities));
    }

    public static int solution(int cacheSize, String[] cities) {
        Cache cache = new Cache(cacheSize);
        Arrays.stream(cities).forEach(city -> cache.run(city.toLowerCase()));
        return cache.getHitRating();
    }

    static class Cache {
        private final int cacheSize;
        private int hitRating;

        private final boolean available;
        private final Deque<String> deque;
        private static final int HIT = 1;
        private static final int MISS = 5;

        public Cache(int cacheSize) {
            this.hitRating = 0;
            this.cacheSize = cacheSize;
            this.available = cacheSize != 0;
            this.deque = new ArrayDeque<>();
        }

        public int getHitRating() {
            return hitRating;
        }

        public void run(String str) {
            hitRating += available ? recordCache(str) : MISS;
        }

        private int recordCache(String str) {
            if (deque.contains(str)) {
                deque.remove(str);
                deque.addLast(str);
                return HIT;
            }
            removeOld();
            deque.addLast(str);
            return MISS;
        }

        private void removeOld() {
            if (isFull()) {
                deque.removeFirst();
            }
        }

        private boolean isFull() {
            return deque.size() == cacheSize;
        }
    }

}
