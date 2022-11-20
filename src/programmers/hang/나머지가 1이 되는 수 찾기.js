function solution(left, right) {
    const array = setArray(left, right);
    return array.map(v => findMeasureCount(v))
        .reduce((x,y,i) => (y % 2 ? -array[i] : array[i]) + x , 0)
}

function setArray(left, right) {
    const array = [];
    for(let i = left; i <= right; i ++) {
        array[i - left] = i;
    }
    return array;
}

function findMeasureCount(num) {
    let count = 1;
    for(let i = 2; i <= num; i ++) {
        num % i === 0 && count++;
    }
    return count;
}