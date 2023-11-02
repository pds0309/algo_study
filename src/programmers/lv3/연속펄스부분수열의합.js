const pulse = [1, -1];

function doPhase(sequence) {
    let maxValue = 0;
    let minValue = 0;
    const cumulativeArr = [];
    sequence.forEach((v, i) => {
        cumulativeArr.push(v * getPulseValue(i) + (cumulativeArr[i - 1] ?? 0));
        maxValue = Math.max(maxValue, cumulativeArr[i]);
        minValue = Math.min(minValue, cumulativeArr[i]);
    });
    return maxValue - minValue;
}

function solution(sequence) {
    return doPhase(sequence);
}

function getPulseValue(idx) {
    return pulse[idx % 2];
}
