function solution(id_list, report, k) {
    function shouldBeNotified(cnt) {
        return cnt >= k;
    }
    const reporterIdxInfo = {};
    id_list.forEach((value, index) => {
        reporterIdxInfo[value] = index;
    });
    const result = Array(id_list.length).fill(0);
    const reporter = {};
    const reportee = {};
    [...new Set(report)].forEach(value => {
        const [er, ee] = value.split(" ");
        reporter[er] ? reporter[er].push(ee) : reporter[er] = [ee];
        reportee[ee] = (reportee[ee] || 0) + 1;
    });
    id_list.forEach(val1 => {
        reporter[val1] &&
        (result[reporterIdxInfo[val1]] +=
            reporter[val1].filter(val2 => shouldBeNotified(reportee[val2])).length);
    });
    return result;
}