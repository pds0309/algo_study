package programmers.lv2;

import java.util.*;
import java.util.stream.Collectors;

public class 단체사진찍기 {
    public static void main(String[] args) {
        System.out.println(solution(2, new String[]{"N~F=0", "R~T>2"}));
    }

    private static int answer = 0;
    private static final Map<String, Operation> operationMap =
            Map.of("=", Operation.EQUAL, ">", Operation.GREATER, "<", Operation.LESS);

    public static int solution(int n, String[] data) {
        int ans = 0;
        List<Relation> relationList = Arrays.stream(data).map(Relation::new).collect(Collectors.toList());
        Set<Friends> friendsSet = Arrays.stream(FriendInfo.values()).map(Friends::new).collect(Collectors.toSet());
        for (Relation relation : relationList) {
            friendsSet.stream().filter(friends -> friends.code == relation.start)
                    .findFirst()
                    .ifPresent(friends ->
                            friends.operation = relation.operation
                    );
        }
        ans = answer;
        answer = 0;
        dfs(friendsSet, 0, relationList);
        return ans;
    }

    private static void dfs(Set<Friends> friendsSet, int cnt, List<Relation> relationList) {
        if (cnt == friendsSet.size()) {
            boolean result;
            for (Relation value : relationList) {
                Friends start = friendsSet.stream()
                        .filter(friends -> value.start.equals(friends.code))
                        .findFirst()
                        .orElse(null);
                Friends end = friendsSet.stream()
                        .filter(friends -> value.end.equals(friends.code))
                        .findFirst()
                        .orElse(null);
                if (start != null && end != null) {
                    result = value.operation.operate(start, end, value.value);
                    if (!result) {
                        return;
                    }
                }
            }
            answer++;
        }
        for (Friends friends : friendsSet) {
            if (friends.index == -1) {
                friends.index = cnt;
                dfs(friendsSet, cnt + 1, relationList);
                friends.index = -1;
            }
        }
    }

    static class Friends {
        FriendInfo code;
        Operation operation;

        int index = -1;

        public Friends(FriendInfo code) {
            this.code = code;
            this.operation = null;
        }
    }

    static class Relation {
        FriendInfo start;
        FriendInfo end;
        Operation operation;
        int value;

        public Relation(String value) {
            this.start = FriendInfo.valueOf(String.valueOf(value.charAt(0)));
            this.end = FriendInfo.valueOf(String.valueOf(value.charAt(2)));
            this.operation = operationMap.get(String.valueOf(value.charAt(3)));
            this.value = value.charAt(4) - 48;
        }
    }

    enum FriendInfo {
        A, C, F, J, M, N, R, T;
    }

    enum Operation implements Operator {
        EQUAL((friends, friends2, val) -> Math.abs(friends.index - friends2.index) == val + 1),
        GREATER((friends, friends2, val) -> Math.abs(friends.index - friends2.index) > val + 1),
        LESS((friends, friends2, val) -> Math.abs(friends.index - friends2.index) < val + 1);

        private final Operator operator;

        Operation(Operator operator) {
            this.operator = operator;
        }

        @Override
        public boolean operate(Friends first, Friends second, int val) {
            return this.operator.operate(first, second, val);
        }
    }

    interface Operator {
        boolean operate(Friends first, Friends second, int val);
    }
}
