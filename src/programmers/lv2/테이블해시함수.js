function solution(data, col, row_begin, row_end) {
    const sortedData = data.sort((o1, o2) => o2[col - 1] !== o1[col - 1] ? o1[col - 1] - o2[col - 1] : o2[0] - o1[0]);
    return sortedData.map((d, i)
        => d.reduce((x, y) => x + y % (i + 1), 0))
        .slice(row_begin - 1, row_end)
        .reduce((x, y) => x ^ y);
}