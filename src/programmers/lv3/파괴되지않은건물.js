const METHODS = [0, -1, 1];

function solution(board, skill) {
    const cumulativeArr = [...new Array(board.length + 1)].map(_ => Array.from({length: board[0].length + 1}, () => 0));
    skill.forEach(sk => fillCumulativeArrBySkill(cumulativeArr, sk));
    return getPositiveCountByCumulativeArr(cumulativeArr, board);
}

function getPositiveCountByCumulativeArr(cumArr, realArr) {
    let count = 0;
    realArr.forEach((arr, i) => {
        arr.forEach((_, j) => {
            cumArr[i][j] += getValueOrZero(cumArr, i, j - 1) + getValueOrZero(cumArr, i - 1, j) - getValueOrZero(cumArr, i - 1, j - 1);
            if(cumArr[i][j] + realArr[i][j] > 0) {
                count++;
            }
        });
    })
    return count;
}

function getValueOrZero(arr, x, y) {
    return (x === -1 || y === -1) ? 0 : arr[x][y];
}

function getMethodAmount (methodIdx, degree) {
    return METHODS[methodIdx] * degree;
}

function fillCumulativeArrBySkill(arr, skill) {
    const amount = getMethodAmount(skill[0] , skill.at(-1));
    arr[skill[1]][skill[2]] += amount;
    arr[skill[1]][skill[4] + 1] -= amount;
    arr[skill[3] + 1][skill[4] + 1] += amount;
    arr[skill[3] + 1][skill[2]] -= amount;
}
