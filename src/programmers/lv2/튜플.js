function solution(s) {
    const map = new Map();
    s.replaceAll("{", "").replaceAll("}", "").split(",").forEach(value => {
        map.set(value, (map.get(value) || 1) + 1);
    });
    return [...map.entries()].sort((o1,o2) => o2[1] - o1[1]).map(val => parseInt(val[0]));
}