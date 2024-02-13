import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class boj2257_화학식량 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] inputs = scanner.nextLine().toCharArray();
        Deque<Integer> deque = new ArrayDeque<>();
        for (char atom : inputs) {
            if (atom == '(') {
                deque.offerLast(0);
                continue;
            }
            if (atom == ')') {
                deque.offerLast(getAllAtomsAmount(deque, 0));
                continue;
            }
            if (Character.isDigit(atom) && !deque.isEmpty()) {
                deque.offerLast(deque.pollLast() * (atom - '0'));
                continue;
            }
            deque.offerLast(Atom.valueOf(Character.toString(atom)).amount);
        }

        System.out.println(getAllAtomsAmount(deque, -1));
    }

    public static int getAllAtomsAmount(Deque<Integer> deque, int breakValue) {
        int amount = 0;
        while (!deque.isEmpty()) {
            if (deque.peekLast() == breakValue) {
                deque.pollLast();
                break;
            }
            amount += deque.pollLast();
        }
        return amount;
    }

    enum Atom {
        H(1), C(12), O(16);

        private final int amount;

        Atom(int amount) {
            this.amount = amount;
        }

    }

}
