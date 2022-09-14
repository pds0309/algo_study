package programmers.lv3;

import java.util.*;
import java.util.stream.Collectors;

public class 불량사용자 {

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
        System.out.println(solution(user_id, banned_id));
    }

    public static int solution(String[] user_id, String[] banned_id) {
        String[] bannedRegex = Arrays.stream(banned_id)
                .map(s -> s.replaceAll("\\*", "[a-z0-9]"))
                .toArray(String[]::new);
        User[] users = Arrays.stream(user_id)
                .map(User::new)
                .toArray(User[]::new);
        List<User>[] bannedByArray = new List[bannedRegex.length];
        for (int i = 0; i < bannedByArray.length; i++) {
            bannedByArray[i] = new ArrayList<>();
        }
        for (int i = 0; i < bannedRegex.length; i++) {
            int finalI = i;
            bannedByArray[i].addAll(Arrays.stream(users)
                    .filter(user -> user.id.matches(bannedRegex[finalI]))
                    .collect(Collectors.toList()));
        }
        pe(0, bannedByArray, 0);
        return set.size();
    }

    static Set<List<User>> set = new HashSet<>();

    private static void pe(int count, List<User>[] bannedArray, int idx) {
        if (idx == bannedArray.length) {
            set.add(Arrays.stream(bannedArray)
                    .flatMap(Collection::stream)
                    .filter(user -> user.glimpsed)
                    .distinct()
                    .collect(Collectors.toList()));
            return;
        }
        for (int j = 0; j < bannedArray[idx].size(); j++) {
            if (!bannedArray[idx].get(j).glimpsed) {
                bannedArray[idx].get(j).glimpsed = true;
                pe(count + 1, bannedArray, idx + 1);
                bannedArray[idx].get(j).glimpsed = false;
            }
        }
    }

    static class User {
        String id;
        boolean glimpsed;

        public User(String id) {
            this.id = id;
            this.glimpsed = false;
        }
    }
}
