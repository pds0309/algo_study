package programmers.lv2;

public class n진수게임 {
    private static final int BOUND = 16;

    public static void main(String[] args) {
        int n = 16;
        int t = 16;
        int m = 2;
        int p = 2;
        System.out.println(solution(n, t, m, p));
    }

    public static String solution(int n, int t, int m, int p) {
        String gameResult = getGameResult(t, m, n);
        return String.valueOf(getMyGameResultArray(gameResult, m, p, t));
    }

    private static String getGameResult(int t, int m, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(0);
        for (int i = 1; i <= t * m; i++) {
            stringBuilder.append(toJinBub(i, n));
        }
        return stringBuilder.toString();
    }

    private static char[] getMyGameResultArray(String gameResult, int m, int p, int t) {
        char[] myArray = new char[t];
        if (m == p) {
            p = 0;
        }
        int currentIndex = 0;
        for (int i = 1; i < gameResult.length() + 1 && currentIndex < t; i++) {
            if (i % m == p) {
                myArray[currentIndex++] = gameResult.charAt(i - 1);
            }
        }
        return myArray;
    }

    private static String toJinBub(int n, int k) {
        StringBuilder stringBuilder = new StringBuilder();
        while (n != 0) {
            int currentNumber = n % k;
            stringBuilder.insert(0,
                    checkTwoDigitNumber(currentNumber) ?
                            getCharacterFromTwoDigitNumber(currentNumber) : currentNumber);
            n = n / k;
        }
        return stringBuilder.toString();
    }


    private static String getCharacterFromTwoDigitNumber(int number) {
        return String.valueOf(((char) (number + 55)));
    }

    private static boolean checkTwoDigitNumber(int number) {
        return number >= 10;
    }
}
