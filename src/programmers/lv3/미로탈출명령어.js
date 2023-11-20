// PRIORITY: D, L, R, U
const X = [1, 0, 0, -1];
const Y = [0, -1, 1, 0];
const C = ["d", "l", "r", "u"];
let answer = "";

function solution(n, m, x, y, r, c, k) {
    const dist = Math.abs(x - r) + Math.abs(y - c);
    if((k - dist) % 2 === 1) {
        return "impossible";
    }
    const dfsResult = dfs(x, y, r, c, dist, n, m, "", k);
    return dfsResult ? answer : 'impossible';
}

function dfs(x, y, r, c, dist, n, m, str, k) {
    if(dist === 0) {
        answer = str;
        return true;
    }
    for(let i = 0 ; i < 4; i ++) {
        const newX = x + X[i];
        const newY = y + Y[i];
        const newC = str + C[i];
        if(!isValidRange(newX, newY, n, m) || dist > k) {
            continue;
        }
        if(dfs(newX, newY, r, c, Math.abs(r - newX) + Math.abs(c - newY), n, m, newC, k - 1)) {
            return true;
        }
    }
    return false;
}

function isValidRange(x, y, n, m) {
    return x >= 1 && y >= 1 && x <= n && y <= m;
}

