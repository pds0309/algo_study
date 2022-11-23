function solution(x) {
    return !(x % (x.toString()).split("").reduce((x,y) => parseInt(x) + parseInt(y)))
}