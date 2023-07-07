function solution(cookie) {
    let answer = 0;
    if (cookie.length === 1) {
        return answer;
    }
    for (let i = 0; i < cookie.length - 1; i++) {
        let idx1 = i;
        let idx2 = i + 1;
        let m1 = cookie[idx1];
        let m2 = cookie[idx2];
        while (true) {
            if (idx1 < 0 || idx2 >= cookie.length) {
                break;
            }
            if (m1 === m2) {
                answer = Math.max(answer, m1);
                m1 += (cookie[--idx1] ?? 0);
                m2 += (cookie[++idx2] ?? 0);
                continue;
            }
            if (m1 < m2) {
                m1 += (cookie[--idx1] ?? 0);
                continue;
            }
            m2 += (cookie[++idx2] ?? 0);
        }
    }
    return answer;
}
