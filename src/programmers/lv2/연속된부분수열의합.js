function solution(sequence, k) {
    const answer = [];
    let start = 0;
    let end = 0;
    let current = 0;
    for(let i = 0; i < sequence.length; i ++) {
        current += sequence[end];
        while(current > k) {
            current -= sequence[start];
            start++;
        }
        if(current === k) {
            answer.push([start ,end]);
        }
        end++;
    }
    answer.sort((o1,o2) => (o1[1] - o1[0]) - (o2[1] - o2[0]));
    return answer[0];
}
