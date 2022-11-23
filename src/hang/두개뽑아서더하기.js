function solution(numbers) {
    const set = new Set();
    numbers.forEach((v,i) => {
        for(let j = i + 1; j < numbers.length; j ++) {
            set.add(v + numbers[j]);
        }
    });
    return [...set].sort((o1, o2) => o1 - o2);
}