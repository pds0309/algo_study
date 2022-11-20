function solution(n, m) {
    const min = Math.min(n,m);
    const max = Math.max(n,m);
    return [gcd(min, max), lcm(min, max)];
}

function gcd(min, max) {
    for(let i = min; i > 1; i --) {
        if(!(min % i) && !(max % i)) {
            return i;
        }
    }
    return 1;
}

function lcm(min , max) {
    return max / gcd(min, max) * min;
}