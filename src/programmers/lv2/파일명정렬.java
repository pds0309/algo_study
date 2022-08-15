package programmers.lv2;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 파일명정렬 {

    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        System.out.println(Arrays.toString(solution(files)));
    }

    public static String[] solution(String[] files) {
        return Arrays.stream(files)
                .map(File::new).sorted()
                .map(File::toString)
                .toArray(String[]::new);
    }

    private static class File implements Comparable<File> {
        private final String head;
        private final String number;
        private final String tail;

        private static final String HEAD_REGEX = "[A-z\\s-.]+";
        private static final String NUMBER_REGEX = "[0-9]{1,5}+";

        public File(String file) {
            this.head = extractTextByRegex(HEAD_REGEX, file);
            this.number = extractTextByRegex(NUMBER_REGEX, file);
            this.tail = file.substring(head.length() + number.length());
        }

        @Override
        public String toString() {
            return head + number + tail;
        }

        @Override
        public int compareTo(File file) {
            if (head.equalsIgnoreCase(file.head)) {
                return Integer.parseInt(number) - Integer.parseInt(file.number);
            }
            return head.compareToIgnoreCase(file.head);
        }

        private String extractTextByRegex(String regex, String str) {
            Matcher matcher = Pattern.compile(regex).matcher(str);
            if (matcher.find()) {
                return matcher.group();
            }
            throw new IllegalArgumentException("주어진 문자열에 일치하는 패턴 없음");
        }
    }
}
