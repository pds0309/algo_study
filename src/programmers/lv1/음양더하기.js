function solution(absolutes, signs) {
    return absolutes.reduce((sum, next, idx) => sum + (signs[idx] ? next : -next), 0)
}