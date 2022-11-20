function solution(n) {
    let count = 0;
    for(let i = 1; i < n; i ++) {
       i % 2 && !(n % i) && count++;
    }
    return n % 2 ? count + 1 : count;
}