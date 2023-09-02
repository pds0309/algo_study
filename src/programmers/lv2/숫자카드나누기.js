function solution(arrayA, arrayB) {
    arrayA.sort((o1, o2) => o1 - o2);
    arrayB.sort((o1, o2) => o1 - o2);
    const divisorsA = getDivisors(arrayA[0]);
    const divisorsB = getDivisors(arrayB[0]);
    return Math.max(getAnswer(arrayA, arrayB, divisorsA), getAnswer(arrayB, arrayA, divisorsB));
}

function getAnswer(arr1, arr2, divisors) {
    for (let i = 0; i < divisors.length; i++) {
        if (arr1.every(a => a % divisors[i] === 0) && !arr2.some(a => a % divisors[i] === 0)) {
            return divisors[i];
        }
    }
    return 0;
}

function getDivisors(val) {
    let answer = [];
    if (val === 1) {
        return answer;
    }
    for (let i = val; i > 1; i--) {
        if (val % i === 0) {
            answer.push(i);
        }
    }
    return answer;
}