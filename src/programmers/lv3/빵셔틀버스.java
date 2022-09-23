package programmers.lv3;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 빵셔틀버스 {
    public static void main(String[] args) {
        int n = 2;
        int t = 10;
        int m = 2;
        String[] timetable = {"09:10", "09:09", "08:00"};
        System.out.println(solution(n, t, m, timetable));
    }

    /**
     * 셔틀버스는 9:00 부터 시작한다.
     *
     * @param n:         운행 횟수이다.
     * @param t:         운행 간격 (분)
     * @param m:         한 셔틀에 탈 수 있는 사람 수
     * @param timetable: 다른 애들 도착 정보
     * @return : 내가 도착할 건데 제일 늦는 시간
     */
    public static String solution(int n, int t, int m, String[] timetable) {
        List<Bus> busList = new ArrayList<>();
        Arrays.sort(timetable);
        LocalTime startTime = getLocalTimeByTTMM("09:00");
        int userIdx = 0;
        for (int i = 0; i < n; i++) {
            busList.add(new Bus(startTime));
            for (int j = 0; j < m; j++) {
                if (userIdx >= timetable.length) {
                    break;
                }
                LocalTime userTime = getLocalTimeByTTMM(timetable[userIdx]);
                Bus currentBus = busList.get(i);
                if (userTime.isAfter(currentBus.startTime)) {
                    break;
                }
                currentBus.setUsers(userTime);
                userIdx++;
            }
            startTime = startTime.plusMinutes(t);
        }

        Bus lastBus = busList.get(busList.size() - 1);
        if (lastBus.isFull(m)) {
            if (lastBus.allSameTime()) {
                return lastBus.users.get(0).minusMinutes(1).toString();
            }
            return lastBus.getMaxBefore().toString();
        }
        return lastBus.startTime.toString();
    }

    private static LocalTime getLocalTimeByTTMM(String ttmm) {
        return LocalTime.parse(ttmm);
    }

    static class Bus {
        LocalTime startTime;
        List<LocalTime> users = new ArrayList<>();

        public Bus(LocalTime startTime) {
            this.startTime = startTime;
        }

        public void setUsers(LocalTime localTime) {
            users.add(localTime);
        }

        public boolean isFull(int range) {
            return this.users.size() == range;
        }

        public boolean allSameTime() {
            return users.get(0).equals(users.get(users.size() - 1));
        }

        public LocalTime getMaxBefore() {
            LocalTime localTime = users.get(users.size() - 1);
            for (int i = users.size() - 1; i >= 0; i--) {
                if (!localTime.equals(users.get(i))) {
                    return localTime.minusMinutes(1);
                }
            }
            return localTime;
        }
    }
}
