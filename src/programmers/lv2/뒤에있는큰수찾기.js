function solution(numbers) {
    const answer = [-1];
    const prevMax = [];
    for(let i = numbers.length - 2; i >= 0; i--) {
        if(numbers[i + 1] > numbers[i]) {
            answer.push(numbers[i + 1]);
            prevMax.push(numbers[i + 1]);
            continue;
        }
        while(prevMax.length !== 0) {
            const maxValue = prevMax.pop();
            if(maxValue > numbers[i]) {
                answer.push(maxValue);
                prevMax.push(maxValue);
                break;
            }
        }
        if(prevMax.length === 0) {
            answer.push(-1);   
        }
    }
    return answer.reverse();
}
