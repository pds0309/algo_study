function solution(want, number, discount) {
    let answer = 0;
    const wantInfo = want.reduce((acc, element, index) => {
        return {...acc, [element]: number[index]}
    }, {})
    
    for(let i = 0 ; i <= discount.length - 10; i ++) {
        const info = {...wantInfo};
        if (!info[discount[i]]) {
            continue;
        }
        for (let j = i; j < 10 + i; j ++) {
            if (info[discount[j]]) {
                info[discount[j]]--;   
            }
            answer += [...Object.values(info)].reduce((x,y) => x + y) === 0 ? 1 : 0;
        }
    }
    return answer;
}