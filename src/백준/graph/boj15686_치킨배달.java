package 백준.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class boj15686_치킨배달 {
    static int len;
    static int chickLimit;
    static int[][] array;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] infos = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        len = infos[0];
        chickLimit = infos[1];
        array = new int[len][len];
        for (int i = 0; i < array.length; i++) {
            array[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        List<ChickenHouse> chickenHouseList = new ArrayList<>();
        List<Client> clientList = new ArrayList<>();
        initChickenAndClient(chickenHouseList, clientList);
        peChicken(clientList, chickenHouseList, 0, 0);
        System.out.println(answer);
    }

    static void findDistance(List<Client> clientList, List<ChickenHouse> chickenHouseList) {
        int cnt = 0;
        for (Client client : clientList) {
            int currentCnt = Integer.MAX_VALUE;
            for (ChickenHouse chickenHouse : chickenHouseList) {
                currentCnt = Math.min(currentCnt, Math.abs(client.x - chickenHouse.x) + Math.abs(client.y - chickenHouse.y));
            }
            cnt += currentCnt;
        }
        answer = Math.min(cnt, answer);
    }

    static void peChicken(List<Client> clientList, List<ChickenHouse> chickenHouseList, int cnt, int idx) {
        if (cnt == chickLimit) {
            findDistance(clientList,
                    chickenHouseList.stream().filter(chickenHouse -> chickenHouse.isAvailable).collect(Collectors.toList()));
            return;
        }
        for (int i = idx; i < chickenHouseList.size(); i++) {
            chickenHouseList.get(i).isAvailable = true;
            peChicken(clientList, chickenHouseList, cnt + 1, i + 1);
            chickenHouseList.get(i).isAvailable = false;
        }
    }

    static class ChickenHouse extends Point {
        boolean isAvailable;

        public ChickenHouse(int x, int y) {
            super(x, y);
        }

    }

    static class Client extends Point {

        public Client(int x, int y) {
            super(x, y);
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void initChickenAndClient(List<ChickenHouse> chickenHouseList, List<Client> clientList) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 1) {
                    clientList.add(new Client(i, j));
                    continue;
                }
                if (array[i][j] == 2) {
                    chickenHouseList.add(new ChickenHouse(i, j));
                    array[i][j] = 0;
                }
            }
        }
    }

}
