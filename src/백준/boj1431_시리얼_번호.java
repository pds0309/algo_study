package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class boj1431_시리얼_번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(bufferedReader.readLine());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            list.add(bufferedReader.readLine());
        }
        list.stream().sorted((o1, o2) -> {
            if (o1.length() == o2.length()) {
                int o1Sum = sumOfStr(o1);
                int o2Sum = sumOfStr(o2);
                if (o1Sum == o2Sum) {
                    return o1.compareTo(o2);
                }
                return o1Sum - o2Sum;
            }
            return o1.length() - o2.length();
        }).forEach(System.out::println);
    }

    static int sumOfStr(String str) {
        return str.chars().filter(value -> value >= 48 && value <= 57).map(operand -> operand - 48).sum();
    }
}
