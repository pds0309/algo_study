const solution = (n, arr1, arr2) => {
    return arr1.map((num, idx) => {
        const biNum = (num | arr2[idx])
            .toString(2)
            .replace(/0|1/g, c => c == 1 ? '#' : ' ');
        return ' '.repeat(arr1.length - biNum.length) + biNum;
    });
}