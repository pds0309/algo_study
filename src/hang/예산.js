function solution(d, budget) {
    return d.sort((o1,o2) => o1 - o2 ).filter(v => {
        if(budget <= 0) {
            return;
        }
        const val = budget - v;
        budget -= v;
        return val >= 0
    }).length
}