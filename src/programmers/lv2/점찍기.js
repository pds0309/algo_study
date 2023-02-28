function solution(k, d) {
    let answer = 0;
    for (let i = 0; i <= d; i += k) {
        const j = Math.floor((d ** 2 - i ** 2) ** 0.5);
        answer += parseInt(j / k) + 1;
    }
    return answer;
}
