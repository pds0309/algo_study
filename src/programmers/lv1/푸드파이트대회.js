function solution(food) {
    const sortedFoodStr = food.slice(1).reduce((x, y, i) => x + String(i + 1).repeat(parseInt(y / 2)), "");
    return sortedFoodStr + "0" + sortedFoodStr.split("").reverse().join("");
}