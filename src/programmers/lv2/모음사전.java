package programmers.lv2;

public class 모음사전 {
    private static final int[] lenCalculator = new int[5];

    public static int solution(String word) {
        initCalculator();
        int answer = 0;
        String[] words = (word).split("");
        for (int i = 0; i < words.length; i++) {
            answer += Word.valueOf(words[i]).calIndex(4 - i);
        }
        return answer;
    }

    enum Word {
        A(0), E(1), I(2), O(3), U(4);
        private final int index;

        Word(int index) {
            this.index = index;
        }

        public int calIndex(int restIdx) {
            return lenCalculator[restIdx] * this.index + this.index;
        }
    }

    private static void initCalculator() {
        for (int i = 1; i < lenCalculator.length; i++) {
            lenCalculator[i] = (int) (lenCalculator[i - 1] + Math.pow(5, i)) + 1;
        }
    }
}
