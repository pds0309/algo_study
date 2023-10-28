function solution(genres, plays) {
    const gs = [...new Set(genres)];
    const summary = gs.map(g =>
        genres
            .map((gen, i) => gen === g ? i : -1)
            .filter(v => v !== -1)
            .reduce((prev, idx) => ({
                ...prev,
                sum: prev.sum + plays[idx],
                arr: prev.arr.concat({val: plays[idx], idx: idx})
            }), {sum: 0, arr: []})
    )
    return summary.sort((o1, o2) => o2.sum - o1.sum)
        .flatMap(v => v.arr.sort((o1, o2) => o2.val - o1.val).slice(0, 2).map(v => v.idx))
}