function solution(num) {
    let count = 0;
    let answer = num;
    while(count < 500) {
        if(answer === 1) {
            return count;
        }
        answer = handleNum(answer);
        count++;
    }
    
    function handleNum(num) {
        return num % 2 ? num * 3 + 1 : num / 2;
    }
    
    return -1;
}