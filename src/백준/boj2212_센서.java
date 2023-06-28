package 백준;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class boj2212_센서 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int centerNum = Integer.parseInt(scanner.nextLine());
        int[] cencorArr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        if (centerNum > cencorArr.length) {
            System.out.println(0);
            return;
        }
        int[] remainedArr = new int[cencorArr.length - 1];
        for (int i = 1; i < cencorArr.length; i++) {
            remainedArr[i - 1] = cencorArr[i] - cencorArr[i - 1];
        }
        long answer = Arrays.stream(remainedArr)
                .sorted()
                .boxed()
                .collect(Collectors.toList())
                .subList(0, remainedArr.length - centerNum + 1)
                .stream().reduce(Integer::sum)
                .orElse(0);
        System.out.println(answer);
    }
}
