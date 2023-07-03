package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj4386_별자리_만들기 {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(bufferedReader.readLine());
        parents = new int[len];
        Star[] starArr = new Star[len];

        for (int i = 0; i < len; i++) {
            double[] starInfo = Arrays.stream(bufferedReader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
            Star star = new Star(i, starInfo[0], starInfo[1]);
            starArr[i] = star;
            parents[i] = i;
        }

        List<StarEdge> edgeList = new ArrayList<>();
        for (int i = 0; i < starArr.length - 1; i++) {
            for (int j = i + 1; j < starArr.length; j++) {
                double dist = calDist(starArr[i].x, starArr[j].x, starArr[i].y, starArr[j].y);
                edgeList.add(new StarEdge(starArr[i].id, starArr[j].id, dist));
            }
        }
        double answer = 0.0;
        int cnt = 0;
        edgeList.sort(Comparator.comparingDouble(o -> o.dist));
        for (StarEdge starEdge : edgeList) {
            if (find(starEdge.start) == find(starEdge.end)) {
                continue;
            }
            union(starEdge.start, starEdge.end);
            answer += starEdge.dist;
            cnt++;
            if (cnt == len - 1) {
                break;
            }
        }
        System.out.println(answer);
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        }
        parents[y] = x;
        return true;
    }

    static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    static double calDist(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
    }

    static class StarEdge {
        int start;
        int end;
        double dist;

        public StarEdge(int start, int end, double dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }
    }

    static class Star {
        int id;
        double x;
        double y;

        public Star(int id, double x, double y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }
}
