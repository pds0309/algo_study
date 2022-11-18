const solution = n => {
    return [...Array(n).keys()].filter(v => !(n % v)).reduce((x,y) => x + y, n);
}