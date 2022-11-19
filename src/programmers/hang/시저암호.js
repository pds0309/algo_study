function solution(s, n) {
    return s.split("").map(v => v === " " ? v : String.fromCharCode(pushRight(v, n))).join("");
}

function pushRight(char, n) {
    const newLocation = char.charCodeAt(0) + n;
    return newLocation > getMaxCodeRangeByCase(char) ? newLocation - 26 : newLocation;
}

function getMaxCodeRangeByCase(char) {
    return char.toUpperCase() === char ? 90 : 122;
}