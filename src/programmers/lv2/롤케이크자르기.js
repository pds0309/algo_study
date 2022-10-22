function solution(topping) {
    let answer = 0;
    const toppingInfo = new Map();
    topping.forEach((val) => {
        toppingInfo.set(val, (toppingInfo.get(val) || 0) + 1);        
    });
    const currentToppingInfo = new Map();
    topping.forEach((val) => {
        currentToppingInfo.set(val, (currentToppingInfo.get(val) || 0) + 1);
        toppingInfo.set(val ,toppingInfo.get(val) - 1);
        toppingInfo.get(val) === 0 && toppingInfo.delete(val);
        currentToppingInfo.size === toppingInfo.size && answer++;
    });
    return answer;
}