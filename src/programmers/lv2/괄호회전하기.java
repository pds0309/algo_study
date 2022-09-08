package programmers.lv2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.stream.Collectors;

public class 괄호회전하기 {
    public static void main(String[] args) {
        String s = "[](){}";
        System.out.println(solution(s));
    }

    static Map<Character, GwalHo> map =
            Map.of('(', new GwalHo("L", "S"), ')', new GwalHo("R", "S"),
                    '{', new GwalHo("L", "M"), '}', new GwalHo("R", "M"),
                    '[', new GwalHo("L", "B"), ']', new GwalHo("R", "B"));

    public static int solution(String s) {
        GwalHoRepo gwalHoRepo = new GwalHoRepo(s);
        gwalHoRepo.track();
        return gwalHoRepo.getCount();
    }

    static class GwalHoRepo {
        Deque<GwalHo> gwalHoDeque = new ArrayDeque<>();
        int count = 0;

        public GwalHoRepo(String s) {
            this.gwalHoDeque.addAll(s.chars()
                    .mapToObj(ch -> map.get((char) ch))
                    .collect(Collectors.toList()));
        }

        public int getCount() {
            return count;
        }

        public void track() {
            if (hasBiasedCount()) {
                return;
            }
            for (GwalHo gwalHo : gwalHoDeque) {
                travelDeque();
                rotate();
            }
        }

        private void travelDeque() {
            Deque<GwalHo> deque = new ArrayDeque<>();
            for (GwalHo gwalHo : gwalHoDeque) {
                if (!gwalHo.direction.operate(deque, gwalHo)) {
                    return;
                }
            }
            count += deque.isEmpty() ? 1 : 0;
        }

        private void rotate() {
            gwalHoDeque.offerLast(gwalHoDeque.pollFirst());
        }

        private boolean hasBiasedCount() {
            return gwalHoDeque.size() % 2 == 1 || gwalHoDeque.stream()
                    .filter(gwalHo -> Direction.R.equals(gwalHo.direction))
                    .count() != gwalHoDeque.size() / 2;
        }
    }

    static class GwalHo {
        Direction direction;
        GwalHoType gwalHoType;

        public GwalHo(String direction, String type) {
            this.direction = Direction.valueOf(direction);
            this.gwalHoType = GwalHoType.valueOf(type);
        }

        public boolean arePair(GwalHo gwalHo) {
            return this.gwalHoType.equals(gwalHo.gwalHoType)
                    && this.direction.equals(Direction.L)
                    && gwalHo.direction.equals(Direction.R);
        }
    }

    enum Direction implements DirectionOperator {
        L((deque, gwalHo) -> deque.offerLast(gwalHo)),
        R((deque, gwalHo) -> !deque.isEmpty() && deque.pollLast().arePair(gwalHo));
        private final DirectionOperator directionOperator;

        Direction(DirectionOperator directionOperator) {
            this.directionOperator = directionOperator;
        }

        @Override
        public boolean operate(Deque<GwalHo> deque, GwalHo gwalHo) {
            return this.directionOperator.operate(deque, gwalHo);
        }
    }

    interface DirectionOperator {
        boolean operate(Deque<GwalHo> deque, GwalHo gwalHo);
    }

    enum GwalHoType {
        B, M, S
    }
}
