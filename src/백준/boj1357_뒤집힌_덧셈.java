package 백준;

import java.util.Arrays;
import java.util.Scanner;

public class boj1357_뒤집힌_덧셈 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = Arrays.stream(
                        new StringBuilder(
                                sc.nextLine()).reverse().toString().split(" "))
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println(Integer.parseInt(new StringBuilder(sum+"").reverse().toString()));
    }
}
