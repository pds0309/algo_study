function solution(strings, n) {
    return strings.sort((o1, o2) => o1[n] === o2[n] ? o1.localeCompare(o2) : o1[n].localeCompare(o2[n]));
}