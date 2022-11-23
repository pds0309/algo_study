const LOTTO_POLICY = {
    "6": 1,
    "5": 2,
    "4": 3,
    "3": 4,
    "2": 5,
    "1": 6,
    "0": 6,
}

function solution(lottos, win_nums) {
    const zeroFilteredLottos = lottos.filter(v => v !== 0);
    const zeroNum = lottos.length - zeroFilteredLottos.length;
    const matchedCount = 12 - new Set(lottos.map((v, i) => !v ? 46 + i : v).concat(win_nums)).size;
    return [LOTTO_POLICY[matchedCount + zeroNum], LOTTO_POLICY[matchedCount]];
}