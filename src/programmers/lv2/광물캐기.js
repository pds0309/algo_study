const level = {
    'diamond': 2,
    'iron': 1,
    'stone': 0,
}

let result = 9999999999999;

function solution(picks, minerals) {
    const [diamond, iron, stone] = picks;
    dfs(diamond, iron, stone, minerals, 0, 0); 
    return result;
}

function dfs(diamond, iron, stone, minerals, idx, score) {
    if(diamond + iron + stone === 0 || minerals.length <= idx) {
        result = Math.min(result, score);
        return;
    }
    if(diamond >= 1) {
        const [earnScore, range] = loopMinerals(minerals, idx, 'diamond');
        dfs(diamond - 1, iron, stone, minerals, idx + range, score + earnScore);
    }
    if(iron >= 1) {
       const [earnScore, range] = loopMinerals(minerals, idx, 'iron');
       dfs(diamond, iron - 1, stone, minerals, idx + range, score + earnScore);
    }
    if(stone >= 1) {
       const [earnScore, range] = loopMinerals(minerals, idx, 'stone');
       dfs(diamond, iron, stone - 1, minerals, idx + range, score + earnScore);
    }
}

function loopMinerals(minerals, startIdx, pickName) {
    let score = 0;
    let range = Math.min(startIdx + 5, minerals.length);
    for(let i = startIdx; i < range; i ++) {
        score += (5 ** Math.max(0, (level[minerals[i]] - level[pickName])));
    }
    return [score, range - startIdx];
}
