package 백준;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class boj17413_단어_뒤집기2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        StringBuilder result = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        AtomicBoolean isTag = new AtomicBoolean(false);
        str.chars().forEach(value -> {
            char val = (char) value;
            if (val == '<') {
                result.append(temp.reverse());
                temp.delete(0, temp.length());
                isTag.set(true);
            }
            if (isTag.get()) {
                result.append(val);
                if (val == '>') {
                    isTag.set(false);
                }
            } else {
                if (val == ' ') {
                    result.append(temp.reverse());
                    temp.delete(0, temp.length());
                    result.append(" ");
                } else {
                    temp.append(val);
                }
            }
        });
        result.append(temp.reverse());
        System.out.println(result);
    }
}
