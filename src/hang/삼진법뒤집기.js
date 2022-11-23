function solution(n) {
    return n.toString(3).split("").reduce((x,y,i) => x + Math.pow(3, i) * y, 0);
}