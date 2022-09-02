function solution(N, stages) {
    const map = new Map();
    [...Array(N)].forEach((v,i)=> map.set(i + 1,0));
    Array.from(map.keys()).forEach((key) => {
        map.set(key, stages.filter(val => val == key).length  / stages.filter(val => val >= key).length);
    });
    return [...map.entries()].sort((o1,o2) => o2[1] - o1[1]).map(v => v[0]);
}