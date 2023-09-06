package boi;

public class 최소공배수 {

    public static void main(String[] args) {
        System.out.println(gcd(8, 12));
        // 최소공배수
        System.out.println(96 / gcd(8, 12));
    }

    // 최대공약수
    public static int gcd(int x, int y) {
        int result = 1;
        for (int i = 1; i <= Math.min(x, y); i++) {
            if (y % i == 0 && x % i == 0) {
                result = i;
            }
        }
        return result;
    }
}
