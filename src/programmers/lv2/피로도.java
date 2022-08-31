package programmers.lv2;

public class 피로도 {
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println(solution(k, dungeons));

        // 123
        // 132
        // 213
        // 231
        // 312
        // 321
    }

    private static int count = 0;

    public static int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        dfs(visited, dungeons, k, 0);
        return count;
    }

    private static void dfs(boolean[] visited, int[][] dungeons, int peeRodo, int cnt) {
        count = Math.max(cnt, count);
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && peeRodo >= dungeons[i][0]) {
                visited[i] = true;
                dfs(visited, dungeons, peeRodo - dungeons[i][1], cnt + 1);
                visited[i] = false;
            }
        }
    }

}
