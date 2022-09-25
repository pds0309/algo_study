function solution(x) {
    return !(x % ((x+"").split("")).reduce((x,y) => parseInt(x) + parseInt(y)));
}