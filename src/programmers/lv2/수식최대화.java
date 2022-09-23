package programmers.lv2;

import java.util.*;
import java.util.function.BinaryOperator;

public class 수식최대화 {
    public static void main(String[] args) {
        String expression = "100-200*300-500+20";
        System.out.println(solution(expression));
    }

    static Map<String, Operator> map = Map.of("+", Operator.PLUS, "-", Operator.MINUS, "*", Operator.MULT);
    static List<String[]> priorities = new ArrayList<>();

    public static long solution(String expression) {
        setPriorities(0, "", new HashSet<>());
        return priorities.stream()
                .mapToLong(value -> Math.abs(order(expression, value, 0)))
                .max()
                .orElse(-1);
    }

    private static long order(String expression, String[] priorities, int idx) {
        if (expression.matches("[0-9]+")) {
            return Long.parseLong(expression);
        }
        List<Long> answerList = new ArrayList<>();
        for (String str : expression.split("\\".concat(priorities[idx]))) {
            answerList.add(order(str, priorities, idx + 1));
        }
        return answerList.stream()
                .reduce((x, y) -> map.get(priorities[idx])
                        .operator.apply(x, y))
                .orElse(0L);
    }

    private static void setPriorities(int count, String operators, Set<String> visited) {
        if (count == 3) {
            priorities.add(operators.split(""));
            return;
        }
        for (String c : map.keySet()) {
            if (!visited.contains(c)) {
                visited.add(c);
                setPriorities(count + 1, operators + c, visited);
                visited.remove(c);
            }
        }
    }

    enum Operator {
        PLUS((a, b) -> a + b),
        MINUS((a, b) -> a - b),
        MULT((a, b) -> a * b);

        private final BinaryOperator<Long> operator;

        Operator(BinaryOperator<Long> operator) {
            this.operator = operator;
        }
    }

}
