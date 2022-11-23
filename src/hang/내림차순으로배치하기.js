function solution(s) {
    const lowers = [];
    const uppers = [];
    s.split("").forEach(v => {
       if(v.toUpperCase() === v) {
           uppers.push(v);
       } else {
           lowers.push(v);
       }
    });
    return lowers.sort((o1,o2) => sort(o1,o2))
        .concat(uppers.sort((o1, o2) => sort(o1,o2)))
        .join("");
}

function sort(o1, o2) {
    return o2.localeCompare(o1);
}