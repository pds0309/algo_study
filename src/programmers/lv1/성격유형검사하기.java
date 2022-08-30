package programmers.lv1;

import java.util.Arrays;

public class 성격유형검사하기 {
    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choice = {5, 3, 2, 7, 5};
        System.out.println(solution(survey, choice));
    }

    public static String solution(String[] survey, int[] choices) {
        Surveyee surveyee = new Surveyee();
        for (int i = 0; i < survey.length; i++) {
            Personality personality = Personality.valueOf(survey[i].substring(0, 1));
            surveyee.reflectSurvey(personality, choices[i]);
        }
        return surveyee.getResult();
    }


    enum Personality {
        R(0, true), C(1, true), J(2, true), A(3, true),
        T(0, false), F(1, false), M(2, false), N(3, false);

        private final int order;
        private final boolean positive;

        Personality(int order, boolean positive) {
            this.order = order;
            this.positive = positive;
        }

        public int getFigure(int choice) {
            return this.positive ? 4 - choice : choice - 4;
        }

        public static Personality[] getByPositive(boolean pos) {
            return Arrays.stream(values())
                    .filter(personality -> personality.positive == pos)
                    .toArray(Personality[]::new);
        }
    }
    static class Surveyee {

        private static final Personality[] positive = Personality.getByPositive(true);
        private static final Personality[] negative = Personality.getByPositive(false);
        private final int[] results;

        public Surveyee() {
            this.results = new int[Personality.values().length / 2];
        }

        public void reflectSurvey(Personality personality, int choice) {
            results[personality.order] += personality.getFigure(choice);
        }

        public String getResult() {
            String result = "";
            for (int i = 0; i < results.length; i++) {
                result += results[i] >= 0 ? positive[i] : negative[i];
            }
            return result;
        }
    }
}
