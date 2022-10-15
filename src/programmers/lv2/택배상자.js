function solution(order) {
    let count = 0;
    const stack = [...Array.from(Array(order.length + 1).keys())];
    const left = stack.slice(1, order[0] + 1);
    let right = order[0] + 1;
    
    for(let i = 0; i < order.length; i++) {
        const current = order[i];
        if(left[left.length -1] > current) {
            return count;
        }
        if(left[left.length - 1] === current) {
            left.pop();
            count++;
            continue;
        }
        if(right === current) {
            right++;
            count++;
            continue
        }
        if(right < current) {
            for(let i = right; i < current; i++) {
                left.push(i);
                right++;
            }
            i--;
        }
    } 
    
    return count;
}