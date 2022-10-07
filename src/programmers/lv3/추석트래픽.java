package programmers.lv3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class 추석트래픽 {
    private static final long NANO_CONVERT_VALUE = 1000000000L;

    public static void main(String[] args) {
        String[] lines = {"2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"};
        System.out.println(solution(lines));
    }

    // 1초 : 1000000000 나노
    //LocalDateTime
    // 기준의 시작보다 비교하는 애의 시작점이 크면서도 1초후보다는 작아야 한다.
    // 비교하는애의 끝점이 시작지점보다 크면서 비교하는애의 시작점이 기준의 시작점보다 작아야 한다.
    public static int solution(String[] lines) {
        int answer = 0;
        List<Traffic> traffics = Arrays.stream(lines).map(Traffic::new).collect(Collectors.toList());
        PriorityQueue<LocalDateTime> priorityQueue = new PriorityQueue<>(Comparator.naturalOrder());

        for (Traffic traffic : traffics) {
            priorityQueue.add(traffic.startTime);
            priorityQueue.add(traffic.endTime);
        }
        while (!priorityQueue.isEmpty()) {
            LocalDateTime startTime = priorityQueue.poll();
            LocalDateTime endTime = startTime.plusNanos(NANO_CONVERT_VALUE - NANO_CONVERT_VALUE / 1000);
            long count = traffics.stream().filter(traffic ->
                    isSameOrAfterThenSecond(traffic.startTime, startTime) && isSameOrBeforeThenSecond(traffic.startTime, endTime)
                            || isSameOrAfterThenSecond(traffic.endTime, startTime) && isSameOrBeforeThenSecond(traffic.startTime, startTime)
            ).count();
            answer = (int) Math.max(count, answer);
        }
        return answer;
    }

    static class Traffic {
        private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime endTime;
        LocalDateTime startTime;
        long nanos;

        public Traffic(String line) {
            String[] lines = line.split(" ");
            this.endTime = LocalDateTime.parse(lines[0] + " " + lines[1], dateTimeFormatter);
            this.nanos = (long) (((Double.parseDouble(lines[2].replace("s", "")) - 0.001) * NANO_CONVERT_VALUE));
            this.startTime = endTime.minusNanos(nanos);
        }

    }

    public static boolean isSameOrBeforeThenSecond(LocalDateTime first, LocalDateTime second) {
        return first.equals(second) || first.isBefore(second);
    }

    public static boolean isSameOrAfterThenSecond(LocalDateTime first, LocalDateTime second) {
        return first.equals(second) || first.isAfter(second);
    }
}
