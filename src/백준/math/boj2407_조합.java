package 백준.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class boj2407_조합 {
    private static final BigInteger[] factoArray = new BigInteger[101];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = Arrays.stream(bufferedReader.readLine()
                        .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        initFacto(array[0]);
        System.out.println(getCombination(array[0], array[1]));
    }

    private static void initFacto(int maxValue) {
        factoArray[1] = BigInteger.valueOf(1);
        for (int i = 2; i <= maxValue; i++) {
            factoArray[i] = factoArray[i - 1].multiply(BigInteger.valueOf(i));
        }
    }

    private static BigInteger getCombination(int n, int m) {
        return factoArray[n].divide((factoArray[m].multiply(factoArray[n - m])));
    }

}
