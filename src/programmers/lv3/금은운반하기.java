package programmers.lv3;

public class 금은운반하기 {
    public static void main(String[] args) {
        int a = 90;
        int b = 500;
        int[] g = {70, 70, 0};
        int[] s = {0, 0, 500};
        int[] w = {100, 100, 2};
        int[] t = {4, 8, 1};

        //10, 10, [100], [100], [7], [10]

        int a2 = 10;
        int b2 = 10;
        int[] g2 = {100};
        int[] s2 = {100};
        int[] w2 = {7};
        int[] t2 = {10};
//        System.out.println(solution(a, b, g, s, w, t));
        System.out.println(solution(a2, b2, g2, s2, w2, t2));
    }

    private static long amountOfGold;
    private static long amountOfSilver;

    // a: 금 요구량
    // b: 은 요구량
    // g[]: 금
    // s[]: 은
    // w 최대운송량
    // t[]: 편도 이동 시간
    public static long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        amountOfGold = a;
        amountOfSilver = b;
        City[] cities = new City[w.length];
        for (int i = 0; i < cities.length; i++) {
            cities[i] = new City(g[i], s[i], w[i], t[i]);
        }
        return searchTime(cities);
    }

    private static long searchTime(City[] cities) {
        long min = 0;
        long max = (long) Math.pow(10, 17);
        while (min < max) {
            long mid = (min + max) / 2;
            if (allDelivered(cities, mid)) {
                max = mid;
                continue;
            }
            min = mid + 1;
        }
        return max;
    }

    private static boolean allDelivered(City[] cities, long time) {
        City newCity = new City(0, 0, 0, time);
        for (City city : cities) {
            if (!newCity.isValidTime(city.time)) {
                continue;
            }
            newCity.put(((time - city.time) / (city.time * 2)) + 1, city);
            if (newCity.hasAcceptableAmount()) {
                return true;
            }
        }
        return false;
    }

    static class City {
        private long gold;
        private long silver;
        private long weight;
        private long time;

        public City(long gold, long silver, long weight, long time) {
            this.gold = gold;
            this.silver = silver;
            this.weight = weight;
            this.time = time;
        }

        public boolean isValidTime(long time) {
            return this.time >= time;
        }

        public void put(long count, City currentCity) {
            long currentTotalWeight = count * currentCity.weight;
            this.weight += Math.min(currentTotalWeight, currentCity.gold + currentCity.silver);
            this.gold += Math.min(currentTotalWeight, currentCity.gold);
            this.silver += Math.min(currentTotalWeight, currentCity.silver);
        }

        public boolean hasAcceptableAmount() {
            return this.gold >= amountOfGold && this.silver >= amountOfSilver && this.weight >= amountOfSilver + amountOfGold;
        }
    }

}
