package 백준.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class boj1644_소수의_연속합 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        if (num == 1) {
            System.out.println(0);
            return;
        }
        List<Integer> primes = getPrimes(num);
        int front = 0;
        int rear = 0;
        int sum = 0;
        int answer = 0;
        for (int i = 0; i < primes.size() && front < primes.size(); ) {
            if (sum < num) {
                sum += primes.get(rear++);
                i++;
                continue;
            }
            if (sum >= num) {
                answer += sum == num ? 1 : 0;
                sum -= primes.get(front++);
            }
        }
        answer += primes.get(primes.size() - 1) == num ? 1 : 0;
        System.out.println(answer);
    }

    static List<Integer> getPrimes(int num) {
        boolean[] prime = new boolean[num + 1];
        prime[0] = true;
        prime[1] = true;
        for (int i = 2; i * i <= num; i++) {
            if (prime[i]) {
                continue;
            }
            for (int j = i * i; j <= num; j += i) {
                prime[j] = true;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= num; i++) {
            if (!prime[i]) {
                list.add(i);
            }
        }
        return list;
    }
}
