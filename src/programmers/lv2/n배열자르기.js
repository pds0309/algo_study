function solution(n, left, right) {
    function getValue(index) {
        return parseInt(Math.max(index / n + 1, index % n + 1));
    }

    return [...Array(right - left + 1)].map(val => getValue(n, left++))
}