const DART_BONUS = {'S': 1, 'D': 2, 'T': 3};

function solution(dartResult) {
    const darts = [...dartResult.matchAll(/[0-9]+[SDT][*#]?/g)].map(v => v[0]);
    let doubledValue = 0;
    let tempPrev = 0;
    return darts.reduce((prev, next) => {
        const [resp, prevDouble] = dartToScore(next);
        if(prevDouble) {
            doubledValue += tempPrev;
        }
        tempPrev = resp;
        return prev + resp;
    }, 0) + doubledValue;
}

function dartToScore(dart) {
    const currentScore = parseInt(dart);
    const rest = dart.replace(currentScore, "");
    const result = Math.pow(currentScore, DART_BONUS[rest[0]]);
    if(!rest[1]) {
        return [result , false];
    }
    return rest[1] === '*' ? [result * 2, true] : [result * -1, false];
}