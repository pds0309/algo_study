function solution(s) {
    const sorted = s.split(" ").sort((o1, o2) => o1 - o2);
    return sorted[0] + " " + sorted.pop();
}