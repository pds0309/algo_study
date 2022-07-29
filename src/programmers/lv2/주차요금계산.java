package programmers.lv2;

import java.util.*;
import java.util.stream.Collectors;

public class 주차요금계산 {

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT",
                "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        System.out.println(Arrays.toString(solution(fees, records)));
    }

    public static int[] solution(int[] fees, String[] records) {
        Fee fee = new Fee(fees);
        List<Record> recordList =
                Arrays.stream(records)
                        .map(recStr -> {
                            Record recordByStr = new Record(recStr.split(" "));
                            CarLogRepository.add(new CarLog(recordByStr.carId));
                            return new Record(recStr.split(" "));
                        })
                        .collect(Collectors.toList());

        recordList.forEach(rec ->
                CarLogRepository
                        .findById(rec.carId)
                        .setTime(rec.isIn, rec.time));

        return CarLogRepository.findAll().stream()
                .mapToInt(carLog -> fee.calculatePayByTime(carLog.pollAllTime()))
                .toArray();
    }

    static class Record {
        int time;
        int carId;
        boolean isIn;

        public Record(String[] record) {
            this.time = convertTimeToIntegerMinute(record[0]);
            this.carId = Integer.parseInt(record[1]);
            this.isIn = setIsIn(record[2]);
        }

        private int convertTimeToIntegerMinute(String time) {
            int[] timeArray = Arrays.stream(time.split(":"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            return timeArray[0] * 60 + timeArray[1];
        }

        private boolean setIsIn(String status) {
            return "IN".equals(status);
        }

    }

    static class CarLog {
        private final int id;

        private final PriorityQueue<Integer> inTimeLogQueue = new PriorityQueue<>();
        private final PriorityQueue<Integer> outTimeLogQueue = new PriorityQueue<>();

        public CarLog(int id) {
            this.id = id;
            this.outTimeLogQueue.offer(23 * 60 + 59);
        }

        public void setTime(boolean isIn, int time) {
            if (isIn) {
                inTimeLogQueue.offer(time);
                return;
            }
            outTimeLogQueue.offer(time);
        }

        public int pollAllTime() {
            int sum = 0;
            while (!inTimeLogQueue.isEmpty()) {
                sum += (outTimeLogQueue.poll() - inTimeLogQueue.poll());
            }
            return sum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CarLog carLog = (CarLog) o;
            return id == carLog.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    static class Fee {
        private final int defaultTime;
        private final int defaultPay;
        private final int overTime;
        private final int overPay;

        public Fee(int[] fees) {
            this.defaultTime = fees[0];
            this.defaultPay = fees[1];
            this.overTime = fees[2];
            this.overPay = fees[3];
        }

        public int calculatePayByTime(int time) {
            int resTime = Math.max(0, time - defaultTime);
            return getOverPayByTime(resTime) + defaultPay;
        }

        private int getOverPayByTime(int resTime) {
            return (int) (Math.ceil((double) resTime / overTime) * overPay);
        }
    }

    static class CarLogRepository {
        private static final SortedMap<Integer, CarLog> carLogMap = new TreeMap<>();

        private CarLogRepository() {
        }

        public static void add(CarLog carLog) {
            carLogMap.putIfAbsent(carLog.id, carLog);
        }

        public static CarLog findById(int id) {
            return carLogMap.get(id);
        }

        public static List<CarLog> findAll() {
            return new ArrayList<>(carLogMap.values());
        }
    }
}
