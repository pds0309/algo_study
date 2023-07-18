package 백준;

import java.util.Scanner;
import java.util.regex.Pattern;

public class boj1439_뒤집기 {
    public static void main(String[] args) {
        String s = new Scanner(System.in).nextLine();
        System.out.println(Math.min(Pattern.compile("0+").matcher(s).results().count(), Pattern.compile("1+").matcher(s).results().count()));
    }
}
