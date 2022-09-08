package programmers.lv2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 후보키 {
    static History history = new History();
    static Value[] values;
    static boolean[] visited;

    public static int solution(String[][] relation) {
        values = new Value[relation[0].length];
        for (String[] strings : relation) {
            for (int j = 0; j < strings.length; j++) {
                if (values[j] == null) {
                    values[j] = new Value();
                }
                values[j].add(strings[j]);
            }
        }
        visited = new boolean[values.length];
        findUniqueness();
        history.setMinimality();

        return (int) history.set.stream()
                .filter(combinedValue -> !combinedValue.disabled)
                .count();
    }

    static void findUniqueness() {
        for (int i = 0; i < values.length; i++) {
            combi(0, i + 1);
        }
    }

    static void combi(int start, int r) {
        if (r == 0) {
            List<Value> concatValues = findAllValues(values, visited);
            if (!concatValues.stream().reduce(Value::concat).get().duplicated()) {
                history.add(new CombinedValue(concatValues));
            }
            return;
        }
        for (int i = start; i < values.length; i++) {
            visited[i] = true;
            combi(i + 1, r - 1);
            visited[i] = false;
        }
    }

    static List<Value> findAllValues(Value[] values, boolean[] visited) {
        return IntStream.range(0, visited.length)
                .filter(idx -> visited[idx])
                .mapToObj(filteredIdx -> values[filteredIdx])
                .collect(Collectors.toList());
    }

    static class Value {
        List<String> list = new ArrayList<>();

        public void add(String str) {
            this.list.add(str);
        }

        public List<String> getList() {
            return list;
        }

        public Value concat(Value value) {
            Value newValue = new Value();
            for (int i = 0; i < this.list.size(); i++) {
                newValue.list.add(this.list.get(i) + "FURK" + value.getList().get(i));
            }
            return newValue;
        }

        public boolean duplicated() {
            return this.list.size() != this.list.stream().distinct().count();
        }
    }

    static class History {
        List<CombinedValue> set = new ArrayList<>();

        public void add(CombinedValue values) {
            set.add(values);
        }

        public void setMinimality() {
            for (int i = 0; i < this.set.size(); i++) {
                CombinedValue tempSet = this.set.get(i);
                if (tempSet.disabled) {
                    continue;
                }
                for (int j = i + 1; j < this.set.size(); j++) {
                    if (this.set.get(j).containAll(tempSet)) {
                        this.set.get(j).disabled = true;
                    }
                }
            }
        }
    }

    static class CombinedValue {
        List<Value> values;
        boolean disabled = false;

        public CombinedValue(List<Value> values) {
            this.values = values;
        }

        public boolean containAll(CombinedValue combinedValue) {
            int count = 0;
            for (int i = 0; i < combinedValue.values.size(); i++) {
                for (int j = 0; j < this.values.size(); j++) {
                    if (combinedValue.values.get(i) == this.values.get(j)) {
                        count++;
                    }
                }
            }
            return count == combinedValue.values.size();
        }
    }
}