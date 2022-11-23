function solution(n) {
    const arr = [];

    for (let i = 0; i < n + 1; i += 1) {
        arr.push(true);
    }
    
    for (let i = 2; i <= Math.sqrt(n); i += 1) {
        for (let j = i * i; j <= n; j += i) {
            arr[j] = false;
        }
    }
    
    return arr.filter(value => value).length - 2;
}

