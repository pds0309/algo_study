package programmers.lv3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 매칭점수 {
    public static void main(String[] args) {
        String word = "Muzi";
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};
        System.out.println(solution(word, pages));
    }

    static Map<String, WebSite> urlMap = new HashMap<>();

    public static int solution(String word, String[] pages) {
        WebSite[] webSites = new WebSite[pages.length];
        for (int i = 0; i < pages.length; i++) {
            webSites[i] = new WebSite(i, pages[i]);
        }
        for (WebSite webSite : webSites) {
            webSite.findKeyword(word.toLowerCase());
            urlMap.put(webSite.mainUrl, webSite);
        }
        for (WebSite webSite : webSites) {
            webSite.setMatchingScore();
        }
        Arrays.sort(webSites);
        return webSites[0].index;
    }


    static class WebSite implements Comparable<WebSite> {
        int basicScore;
        int linkScore;
        double matchingScore;
        int index;
        String mainUrl;
        String body;
        List<String> linkList = new ArrayList<>();

        public WebSite(int index, String body) {
            this.index = index;
            String[] split = body.split("<body>");
            setMainUrl(split[0]);
            this.body = split[1].split("</body>")[0];
            seperateBody();
            this.body = (" " + this.body
                    .replaceAll("<[a-zA-Z0-9.:/\"]+>", " ")
                    .replaceAll("<[a-zA-Z0-9.:/\"]+/>", " ") + " ").toLowerCase();
        }

        private void setMainUrl(String head) {
            Matcher matcher = Pattern.compile("<meta property=\"og:url\" content=\"[a-zA-Z0-9.:/]+\"/>").matcher(head);
            if (matcher.find()) {
                this.mainUrl = matcher.group().split("content=\"")[1].replace("\"/>", "");
            }
        }

        private void seperateBody() {
            Matcher matcher = Pattern.compile("<a href=\"[a-zA-Z0-9./:]+\">").matcher(this.body);
            while (matcher.find()) {
                String result = matcher.group();
                this.body = this.body.replace(result, " ");
                this.linkList.add(result.split("<a href=\"")[1].replace("\">", ""));
            }
            this.linkScore = linkList.size();
        }

        public void findKeyword(String keyword) {
            int start = 0;
            int startAt = 0;
            while (true) {
                start = this.body.indexOf(keyword, startAt);
                startAt = start + keyword.length() - 1;
                if (start == -1) {
                    break;
                }
                if (start != 0 && Character.isAlphabetic(this.body.charAt(start))) {
                    continue;
                }
                if (Character.isAlphabetic(this.body.charAt(start + keyword.length()))) {
                    continue;
                }
                this.basicScore++;
            }
        }

        public void setMatchingScore() {
            linkList.forEach(s -> {
                if (urlMap.containsKey(s)) {
                    urlMap.get(s).matchingScore += (double) this.basicScore / this.linkScore;
                }
            });
        }

        @Override
        public int compareTo(WebSite o) {
            if (this.matchingScore + this.basicScore > o.matchingScore + o.basicScore) {
                return -1;
            }
            return 1;
        }
    }
}
