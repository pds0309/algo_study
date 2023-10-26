function solution(n, stations, w) {
    return stations.concat(n + w + 1).reduce((prev, curr) => {
        return ([getCountByWidth((curr - (prev[1] + w) - 1), w * 2 + 1) + prev[0], curr + w]); 
    }, [0,0])[0];
}

function getCountByWidth(val, width) {
    if(val < 0) {
        return 0;
    }
    return val % width === 0 ? val / width : (parseInt(val / width) + 1); 
}
