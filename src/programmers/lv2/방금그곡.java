package programmers.lv2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 방금그곡 {
    public static void main(String[] args) {
        String m = "ABCDEFG";
        String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        System.out.println(solution(m, musicinfos));
    }

    public static String solution(String m, String[] musicinfos) {
        PriorityQueue<MusicInfo> matchedMusics = new PriorityQueue<>();
        for (String musicinfo : musicinfos) {
            MusicInfo musicInfo = new MusicInfo(musicinfo.split(","));
            if (musicInfo.containSounds(m)) {
                matchedMusics.add(musicInfo);
            }
        }
        return matchedMusics.isEmpty() ? "(None)" : matchedMusics.poll().getName();
    }

    private static class MusicInfo implements Comparable<MusicInfo> {
        private final int length;
        private final String name;
        private final String sounds;
        private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH/mm:ss");
        private static final String PRE_DATE_INFO = "2022/08/18/11/";

        public MusicInfo(String[] info) {
            this.length = calTime(info[0], info[1]);
            this.name = info[2];
            this.sounds = convertMajorPitch(info[3]);
        }

        public String getName() {
            return name;
        }

        public boolean containSounds(String m) {
            String allSounds = sounds.repeat(length / sounds.length()) + sounds.substring(0, length % sounds.length());
            return allSounds.contains(convertMajorPitch(m));
        }

        private int calTime(String start, String end) {
            return (int) Duration.between(
                    LocalDateTime.parse(PRE_DATE_INFO + start, dateTimeFormatter),
                    LocalDateTime.parse(PRE_DATE_INFO + end, dateTimeFormatter)).getSeconds();
        }

        private String convertMajorPitch(String sounds) {
            Matcher matcher = Pattern.compile("[A-Z]#+").matcher(sounds);
            return matcher.replaceAll(res -> String.valueOf(((char) (res.group().charAt(0) + 9))));
        }

        @Override
        public int compareTo(MusicInfo o) {
            return o.length - this.length;
        }
    }
}