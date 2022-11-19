const IDIOTS = [{"id": 1, "arr": [1,2,3,4,5] },
               { "id": 2, "arr": [2,1,2,3,2,4,2,5] },
                { "id": 3, "arr": [3,3,1,1,2,2,4,4,5,5] }
               ];


function solution(answers) {
    const answer = IDIOTS.map(v => getMatchedCount(answers, v));
    const maxMatchedValue = Math.max(...answer);
    return IDIOTS.filter(v => v.res === maxMatchedValue).map(v2 => v2.id).sort();
}

function getMatchedCount(array, idiot) {
    idiot.res = array.filter((v, i) => v === idiot.arr[(i % idiot.arr.length)]).length;
    return idiot.res;
}

