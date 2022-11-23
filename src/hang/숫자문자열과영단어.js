const map = new Map();

(() => {
    map.set(0, 'zero');
    map.set(1, 'one');
    map.set(2, 'two');
    map.set(3, 'three');
    map.set(4, 'four');
    map.set(5, 'five');
    map.set(6, 'six');
    map.set(7, 'seven');
    map.set(8, 'eight');
    map.set(9, 'nine');
})();


function solution(s) {
    map.forEach((v,k) => {
        const val = new RegExp(v, 'g');
        s = s.replace(val, k);
    })
    return parseInt(s);
}