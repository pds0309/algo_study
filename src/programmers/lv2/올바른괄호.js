const solution = (s) => {
    let currentStatus = 0;
    for(const ch of s) {
        currentStatus += ch === '(' ? 1 : -1;
        if(currentStatus < 0) {
            return false;
        }
    }
    return currentStatus === 0;
}