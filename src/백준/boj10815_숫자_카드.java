package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class boj10815_숫자_카드 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        Set<Integer> set = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toSet());
        bufferedReader.readLine();
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).forEach(v -> {
            stringBuilder.append(set.contains(v) ? 1 : 0).append(" ");
        });
        System.out.println(stringBuilder);
    }
}
