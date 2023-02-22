const factoArray = [0, 1];

function solution(n) {
    initFacto(2 * n);
    return parseInt(factoArray[2 * n] / (factoArray[n] * factoArray[n + 1]) + 0.1);
}

function initFacto(n) {
    for (let i = 2; i <= n; i++) {
        factoArray[i] = i * factoArray[i - 1];
    }
}