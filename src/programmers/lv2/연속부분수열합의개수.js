function solution(elements) {
    const eleArray = elements.concat.apply(elements, elements.slice(0, elements.length - 1));
    const answerArray = []
    for(let i = 0; i < elements.length; i ++) {
        const tempArray = [...eleArray];
        for(let j = 1 + i; j < elements.length + i; j ++) {
            tempArray[j] += tempArray[j - 1];
        }
        answerArray.push(tempArray);
    }
    return [...new Set(answerArray.flat())].length;
}