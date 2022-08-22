const useValueState = (defaultValue = 0) => {
    let value = defaultValue;
    function set(inputFunc) {
        value = inputFunc();
        return value;
    }
    function getVal() {
        return value;
    }
    return [getVal, set];
}

const solution = (a) => {
    const [result, setResult] = useValueState(0);
    const numCountArray = Array(a.length).fill(0);
    for (const n of a) {
        numCountArray[n]++;
    }
    a.forEach((_v, index) => {
        const currentResult = result();
        if (numCountArray[index] !== 0 && currentResult < numCountArray[index]) {
            setResult(() => Math.max(currentResult, getLengthByCurrentIdx(a, index)))
        }
    })
    return result() * 2;
}
const getLengthByCurrentIdx = (a, i) => {
    const [length, setLength] = useValueState(0);
    const [skip, setSkip] = useValueState(false);
    const areSameNum = (x1, x2) => {
        return x1 === x2;
    }
    const areCorrectNum = (x1, x2) => {
        return (x1 === i && x2 !== i) || (x1 !== i && x2 === i);
    }
    a.forEach((value, index) => {
        if (skip()) {
            setSkip(() => false);
            return;
        }
        if (areSameNum(a[index], a[index + 1]) || index === a.length - 1) {
            return;
        }
        if (areCorrectNum(a[index], a[index + 1])) {
            setLength(() => length() + 1);
            setSkip(() => true);
        }
    });
    return length();
}