function solution(price, money, count) {
    return Math.max(0, count * (2 * price + (count - 1) * price) / 2 - money);
}