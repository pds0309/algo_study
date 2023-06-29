function solution(n, results) {
    const players = [...Array(n + 1)].map(() => Array(n + 1).fill(false));
    results.forEach(([winner, loser]) => {
        players[winner][loser] = true;
    });
    for (let i = 1; i <= n; i++) {
        setRelation(players, i);
    }
    return getClearResultCount(players, n);
}

function setRelation(players, pointPlayerIndex) {
    for (let i = 1; i < players.length; i++) {
        for (let j = 1; j < players.length; j++) {
            if (i === j || players[i][j]) {
                continue;
            }
            if (players[pointPlayerIndex][j] && players[i][pointPlayerIndex]) {
                players[i][j] = true;
            }
        }
    }
}

function getClearResultCount(players, n) {
    const rowCnts = Array(n + 1).fill(0);
    const colCnts = Array(n + 1).fill(0);
    for (let i = 1; i < players.length; i++) {
        for (let j = 1; j < players.length; j++) {
            rowCnts[i] += players[i][j];
            colCnts[j] += players[i][j];
        }
    }
    let cnt = 0;
    for (let i = 1; i < rowCnts.length; i++) {
        if (rowCnts[i] + colCnts[i] === n - 1) {
            cnt++;
        }
    }
    return cnt;
}