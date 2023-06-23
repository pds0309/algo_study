package 백준.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1004_어린왕자 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int caseNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < caseNum; i++) {
            int[] pointArr = getIntArrFromStr(br.readLine());
            Point startPoint = new Point(pointArr[0], pointArr[1]);
            Point endPoint = new Point(pointArr[2], pointArr[3]);
            int planetNum = Integer.parseInt(br.readLine());
            int overlapNum = 0;
            for (int j = 0; j < planetNum; j++) {
                int[] planetArr = getIntArrFromStr(br.readLine());
                Planet planet = new Planet(planetArr[0], planetArr[1], planetArr[2]);
                if (isPointInPlanet(startPoint, planet) ^ isPointInPlanet(endPoint, planet)) {
                    overlapNum++;
                }
            }
            stringBuilder.append(overlapNum).append("\n");
        }
        System.out.println(stringBuilder);
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    static class Planet {
        int radius;
        Point point;

        public Planet(int x, int y, int radius) {
            this.radius = radius;
            this.point = new Point(x, y);
        }
    }

    static int[] getIntArrFromStr(String str) {
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    static boolean isPointInPlanet(Point point, Planet planet) {
        Point planetPoint = planet.point;
        double distance = Math.sqrt(Math.pow(planetPoint.x - point.x, 2) + Math.pow(planetPoint.y - point.y, 2));
        return distance <= planet.radius;
    }

}
